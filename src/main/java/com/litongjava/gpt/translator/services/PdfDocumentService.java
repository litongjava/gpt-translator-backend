package com.litongjava.gpt.translator.services;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import com.jfinal.template.Engine;
import com.litongjava.db.TableInput;
import com.litongjava.db.activerecord.Db;
import com.litongjava.db.activerecord.Row;
import com.litongjava.gemini.GoogleGeminiModels;
import com.litongjava.gpt.translator.constant.TableNames;
import com.litongjava.gpt.translator.utils.ExecutorServiceUtils;
import com.litongjava.openai.chat.ChatResponseUsage;
import com.litongjava.openai.chat.OpenAiChatResponseVo;
import com.litongjava.openai.client.OpenAiClient;
import com.litongjava.openai.constants.OpenAiModels;
import com.litongjava.table.services.ApiTable;
import com.litongjava.tio.utils.crypto.Md5Utils;
import com.litongjava.tio.utils.environment.EnvUtils;
import com.litongjava.tio.utils.hutool.FileUtil;

public class PdfDocumentService {
  private final Object lock = new Object();

  /**
   * 将文档转换为 Markdown 格式（多并发）
   *
   * @param apiKey OpenAI API 密钥
   * @param data 文档二进制数据
   * @param suffix 文件后缀
   * @return Markdown 文本
   * @throws IOException 可能的IO异常
   * @throws InterruptedException 线程中断异常
   * @throws ExecutionException 执行异常
   */
  public String toMarkdown(byte[] data) throws IOException, InterruptedException, ExecutionException {
    String openaiApiKey = EnvUtils.get("OPENAI_API_KEY");
    return toMarkdown(openaiApiKey, data);
  }

  public String toMarkdown(String apiKey, byte[] data) throws IOException, InterruptedException, ExecutionException {
    String md5 = Md5Utils.digestHex(data);
    Row record = Db.findFirst(TableNames.max_kb_document_markdown_cache, "target,content", Row.by("id", md5));
    boolean exists = false;
    if (record != null) {
      String target = record.getStr("target");
      if (target != null) {
        exists = true;
        File file = new File(target);
        if (file.exists()) {
          return FileUtil.readString(file);
        }
      }

      String content = record.getStr("content");
      if (content != null) {
        return content;
      }
    }

    String suffix = "png";
    List<byte[]> documentBytes = new ArrayList<>();
    int totalPages = 0;
    try (PDDocument document = PDDocument.load(new ByteArrayInputStream(data))) {
      totalPages = document.getNumberOfPages();
      PDFRenderer renderer = new PDFRenderer(document);
      for (int i = 0; i < totalPages; i++) {
        BufferedImage bufferedImage = renderer.renderImageWithDPI(i, 144);
        byte[] imageBytes = toBytes(bufferedImage, suffix);
        documentBytes.add(imageBytes);
      }
    }

    ExecutorService executorService = ExecutorServiceUtils.getExecutorService();
    CompletionService<String> completionService = new ExecutorCompletionService<>(executorService);
    List<Future<String>> futures = new ArrayList<>();
    for (int i = 0; i < documentBytes.size(); i++) {
      byte[] imageBytes = documentBytes.get(i);
      futures.add(completionService.submit(() -> convertPdfPageToMarkdown(apiKey, imageBytes, suffix)));
    }

    // 等待所有任务完成并按页码顺序存储结果
    List<String> markdowns = new ArrayList<>(Collections.nCopies(documentBytes.size(), null));
    for (int i = 0; i < futures.size(); i++) {
      Future<String> future = completionService.take();
      int pageIndex = futures.indexOf(future); // 获取页码
      markdowns.set(pageIndex, future.get()); // 按页码顺序存储结果
    }

    StringBuilder combinedMarkdown = new StringBuilder();
    for (String markdown : markdowns) {
      combinedMarkdown.append(markdown);
    }

    String target = "markdowns/" + md5 + ".md";
    new File(target).getParentFile().mkdirs();
    FileUtil.writeString(combinedMarkdown.toString(), target, "UTF-8");
    Row saveRecord = Row.by("id", md5).set("target", target).set("content", combinedMarkdown.toString());
    if (exists) {
      Db.update(TableNames.max_kb_document_markdown_cache, saveRecord);
    } else {
      Db.save(TableNames.max_kb_document_markdown_cache, saveRecord);
    }

    return combinedMarkdown.toString();
  }

  /**
   * 将 BufferedImage 转换为字节数组
   *
   * @param bufferedImage 图片对象
   * @param suffix 文件后缀
   * @return 字节数组
   * @throws IOException 可能的IO异常
   */
  private byte[] toBytes(BufferedImage bufferedImage, String suffix) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ImageIO.write(bufferedImage, suffix, baos);
    return baos.toByteArray();
  }

  /**
   * 将 PDF 页面转换为 Markdown（多并发）
   *
   * @param apiKey OpenAI API 密钥
   * @param imageBytes 图片字节数组
   * @param suffix 文件后缀
   * @return Markdown 文本
   * @throws IOException 可能的IO异常
   */
  private String convertPdfPageToMarkdown(String apiKey, byte[] imageBytes, String suffix) throws IOException {
    String id = Md5Utils.digestHex(imageBytes);
    String sql = String.format("SELECT content FROM %s WHERE id=?", TableNames.max_kb_document_markdown_page_cache);

    String content = Db.queryStr(sql, id);
    if (content != null) {
      return content;
    }

    String imageName = id + "." + suffix;
    String imagePath = "images/" + imageName;
    File imageFile = new File(imagePath);
    imageFile.getParentFile().mkdirs();
    FileUtil.writeBytes(imageBytes, imageFile);

    long start = System.currentTimeMillis();
    OpenAiChatResponseVo chatResponseVo = null;
    String image_to_text = Engine.use().getTemplate("image_to_text_prompt_en.txt").renderToString();

    chatResponseVo = translate(apiKey, imageBytes, suffix, chatResponseVo, image_to_text);

    content = chatResponseVo.getChoices().get(0).getMessage().getContent();
    if (content.startsWith("```markdown")) {
      content = content.substring(11, content.length() - 3);
    }

    content = chatResponseVo.getChoices().get(0).getMessage().getContent();
    if (content.startsWith("```")) {
      content = content.substring(3, content.length() - 3);
    }

    if (content.startsWith("markdown")) {
      content = content.substring(8);
    }

    ChatResponseUsage usage = chatResponseVo.getUsage();
    TableInput saveInput = TableInput.by("id", id).set("target", imagePath).set("content", content)
        //
        .set("elapsed", System.currentTimeMillis() - start).set("model", OpenAiModels.GPT_4O)
        //
        .set("system_fingerprint", chatResponseVo.getSystem_fingerprint()).set("completion_tokens", usage.getCompletion_tokens())
        //
        .set("prompt_tokens", usage.getPrompt_tokens()).set("total_tokens", usage.getTotal_tokens());

    String cacheContent = Db.queryStr(sql, id);
    if (cacheContent != null) {
      return cacheContent;
    }

    // 同步保存操作，避免多线程同时写入
    synchronized (lock) {
      ApiTable.save(TableNames.max_kb_document_markdown_page_cache, saveInput);
    }
    return content;
  }

  private OpenAiChatResponseVo translate(String apiKey, byte[] imageBytes, String suffix, OpenAiChatResponseVo chatResponseVo, String image_to_text) {
    try {
      chatResponseVo = OpenAiClient.chatWithImage(apiKey, GoogleGeminiModels.GEMINI_2_0_FLASH_EXP, image_to_text, imageBytes, suffix);
    } catch (Exception e) {
      try {
        chatResponseVo = OpenAiClient.chatWithImage(apiKey, GoogleGeminiModels.GEMINI_2_0_FLASH_EXP, image_to_text, imageBytes, suffix);
      } catch (Exception e1) {
        try {
          chatResponseVo = OpenAiClient.chatWithImage(apiKey, GoogleGeminiModels.GEMINI_2_0_FLASH_EXP, image_to_text, imageBytes, suffix);
        } catch (Exception e2) {
          e.printStackTrace();
        }
      }
    }
    return chatResponseVo;
  }
}
