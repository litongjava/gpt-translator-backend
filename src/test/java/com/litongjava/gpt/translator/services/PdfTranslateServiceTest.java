package com.litongjava.gpt.translator.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;

import org.junit.Test;

import com.litongjava.gpt.translator.config.EnjoyEngineConfig;
import com.litongjava.gpt.translator.config.TranslateAdminAppConfig;
import com.litongjava.gpt.translator.vo.TranslatorTextVo;
import com.litongjava.jfinal.aop.Aop;
import com.litongjava.openai.chat.OpenAiChatResponseVo;
import com.litongjava.tio.boot.testing.TioBootTest;
import com.litongjava.tio.utils.environment.EnvUtils;

public class PdfTranslateServiceTest {

  @Test
  public void test() {
    TioBootTest.runWith(TranslateAdminAppConfig.class, EnjoyEngineConfig.class);
    String openaiApiKey = EnvUtils.get("OPENAI_API_KEY");
    Path path = Paths.get("F:\\document\\subject-docs\\04_Chemistry\\CHEM_161\\ch03", "Chapter_3_HW_packet.pdf");
    byte[] fileBytes;
    try {
      fileBytes = Files.readAllBytes(path);
    } catch (IOException e1) {
      e1.printStackTrace();
      return;
    }
    try {
      String markdown = Aop.get(PdfDocumentService.class).toMarkdown(openaiApiKey, fileBytes);
      TranslatorService translatorService = new TranslatorService();
      TranslatorTextVo vo = new TranslatorTextVo();
      vo.setSrcText(markdown);
      vo.setSrcLang("English");
      vo.setDestLang("Chinese");
      OpenAiChatResponseVo repsonseVo = translatorService.translate(vo);
      String content = repsonseVo.getChoices().get(0).getMessage().getContent();
      System.out.println(content);

    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }
}
