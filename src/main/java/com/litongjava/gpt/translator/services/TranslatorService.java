package com.litongjava.gpt.translator.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.template.Engine;
import com.jfinal.template.Template;
import com.litongjava.db.activerecord.Db;
import com.litongjava.db.activerecord.Row;
import com.litongjava.gemini.GoogleGeminiModels;
import com.litongjava.gpt.translator.constant.TableNames;
import com.litongjava.gpt.translator.utils.TokenCounter;
import com.litongjava.gpt.translator.vo.TranslatorTextVo;
import com.litongjava.openai.chat.ChatResponseUsage;
import com.litongjava.openai.chat.OpenAiChatResponseVo;
import com.litongjava.openai.client.OpenAiClient;
import com.litongjava.tio.utils.crypto.Md5Utils;
import com.litongjava.tio.utils.snowflake.SnowflakeIdUtils;

public class TranslatorService {

  // Define the maximum number of tokens per request
  private static final int MAX_TOKENS_PER_REQUEST = 32000;

  /**
   * Translate method that handles splitting the text if it exceeds the token limit.
   */
  public String translate(String chatId, TranslatorTextVo translatorTextVo) {
    String srcText = translatorTextVo.getSrcText();
    String md5 = Md5Utils.getMD5(srcText);
    Row record = Db.findColumnsById(TableNames.max_kb_sentence_tanslate_cache, "dst_text", "md5", md5);
    String dstText = null;
    if (record != null) {
      dstText = record.getStr("dst_text");
      if (dstText != null) {
        return dstText;
      }
    }

    // Split the srcText into chunks if necessary
    List<String> textChunks = splitTextIntoChunks(srcText, MAX_TOKENS_PER_REQUEST);

    StringBuilder translatedBuilder = new StringBuilder();
    long totalStart = System.currentTimeMillis();

    for (String chunk : textChunks) {
      TranslatorTextVo chunkVo = new TranslatorTextVo();
      chunkVo.setSrcLang(translatorTextVo.getSrcLang());
      chunkVo.setDestLang(translatorTextVo.getDestLang());
      chunkVo.setSrcText(chunk);

      long start = System.currentTimeMillis();
      OpenAiChatResponseVo chatResponseVo = this.translate(chunkVo);
      long end = System.currentTimeMillis();

      String translatedChunk = chatResponseVo.getChoices().get(0).getMessage().getContent();
      translatedBuilder.append(translatedChunk).append(" "); // You can adjust the delimiter as needed

      // Optionally, save each chunk's translation to the cache
      saveTranslation(chatId, translatorTextVo, chunk, translatedChunk, chatResponseVo, end - start);
    }

    long totalEnd = System.currentTimeMillis();
    String finalTranslatedText = translatedBuilder.toString().trim();

    // Optionally, save the combined translation to the cache
    saveCombinedTranslation(chatId, translatorTextVo, srcText, finalTranslatedText, totalEnd - totalStart);

    return finalTranslatedText;
  }

  /**
   * Helper method to split text into chunks based on the maximum token limit.
   * This implementation splits the text by sentences to preserve meaning.
   */
  /**
   * Splits text into chunks based on the maximum token limit using a recursive binary splitting approach.
   * This method prioritizes splitting by Markdown structure (e.g., paragraphs) to preserve formatting.
   */
  public List<String> splitTextIntoChunks(String text, int maxTokens) {
    List<String> chunks = new ArrayList<>();
    splitRecursive(text, maxTokens, chunks);
    return chunks;
  }

  private void splitRecursive(String text, int maxTokens, List<String> chunks) {
    int tokenCount = TokenCounter.countTokens(text);
    if (tokenCount <= maxTokens) {
      chunks.add(text.trim());
      return;
    }

    // Attempt to split by Markdown paragraphs
    String[] paragraphs = text.split("\n{2,}");
    if (paragraphs.length > 1) {
      for (String para : paragraphs) {
        splitRecursive(para, maxTokens, chunks);
      }
      return;
    }

    // Attempt to split by Markdown headings
    String[] headings = text.split("(?m)^#{1,6} ");
    if (headings.length > 1) {
      for (String section : headings) {
        // Add the heading back since split removes the delimiter
        String trimmedSection = section.trim();
        if (!trimmedSection.startsWith("#")) {
          trimmedSection = "#" + trimmedSection;
        }
        splitRecursive(trimmedSection, maxTokens, chunks);
      }
      return;
    }

    // If unable to split by structure, perform binary split
    int mid = text.length() / 2;
    // Ensure we split at a whitespace to avoid breaking words
    while (mid < text.length() && !Character.isWhitespace(text.charAt(mid))) {
      mid++;
    }
    if (mid == text.length()) {
      // No whitespace found; force split
      mid = text.length() / 2;
    }

    String firstHalf = text.substring(0, mid);
    String secondHalf = text.substring(mid);

    splitRecursive(firstHalf, maxTokens, chunks);
    splitRecursive(secondHalf, maxTokens, chunks);
  }

  /**
   * Save each chunk's translation to the cache.
   */
  private void saveTranslation(String chatId, TranslatorTextVo originalVo, String srcChunk, String dstChunk, OpenAiChatResponseVo chatResponseVo, long elapsed) {
    String md5 = Md5Utils.getMD5(srcChunk);
    long id = SnowflakeIdUtils.id();
    ChatResponseUsage usage = chatResponseVo.getUsage();
    Row saveRecord = Row.by("id", id).set("md5", md5).set("from", "telegram").set("user_id", chatId).set("src_lang", originalVo.getSrcLang()).set("src_text", srcChunk)
        .set("dst_lang", originalVo.getDestLang()).set("dst_text", dstChunk).set("model", chatResponseVo.getModel()).set("system_fingerprint", chatResponseVo.getSystem_fingerprint())
        .set("completion_tokens", usage.getCompletion_tokens()).set("prompt_tokens", usage.getPrompt_tokens()).set("total_tokens", usage.getTotal_tokens()).set("elapsed", elapsed);
    Db.save(TableNames.max_kb_sentence_tanslate_cache, saveRecord);
  }

  /**
   * Optionally save the combined translation to the cache.
   */
  private void saveCombinedTranslation(String chatId, TranslatorTextVo originalVo, String srcText, String dstText, long elapsed) {
    String md5 = Md5Utils.getMD5(srcText);
    long id = SnowflakeIdUtils.id();
    // Assuming you have a way to get usage metrics for the combined translation
    // This might require aggregating usage from all chunks
    Row saveRecord = Row.by("id", id).set("md5", md5).set("from", "telegram").set("user_id", chatId).set("src_lang", originalVo.getSrcLang()).set("src_text", srcText)
        .set("dst_lang", originalVo.getDestLang()).set("dst_text", dstText).set("elapsed", elapsed);
    Db.save(TableNames.max_kb_sentence_tanslate_cache, saveRecord);
  }

  /**
   * Original translate method that translates a single chunk.
   */
  public OpenAiChatResponseVo translate(TranslatorTextVo translatorTextVo) {
    String srcLang = translatorTextVo.getSrcLang();
    String destLang = translatorTextVo.getDestLang();
    String srcText = translatorTextVo.getSrcText();

    Engine engine = Engine.use();
    Template template = engine.getTemplate("translator_prompt.txt");
    Map<String, String> values = new HashMap<>();

    values.put("src_lang", srcLang);
    values.put("dst_lang", destLang);
    values.put("source_text", srcText);

    String prompt = template.renderToString(values);
    OpenAiChatResponseVo responseVo = withGemini2FlashExp(prompt);

    return responseVo;
  }

  private OpenAiChatResponseVo withGemini2FlashThinkningExp(String prompt) {
    OpenAiChatResponseVo responseVo = null;
    try {
      responseVo = OpenAiClient.chatWithModel(GoogleGeminiModels.GEMINI_2_0_FLASH_THINKING_EXP, "user", prompt);
    } catch (Exception e) {
      try {
        responseVo = OpenAiClient.chatWithModel(GoogleGeminiModels.GEMINI_2_0_FLASH_THINKING_EXP, "user", prompt);
      } catch (Exception e2) {
        responseVo = OpenAiClient.chatWithModel(GoogleGeminiModels.GEMINI_2_0_FLASH_THINKING_EXP, "user", prompt);
      }
    }
    return responseVo;
  }

  private OpenAiChatResponseVo withGemini2FlashExp(String prompt) {
    OpenAiChatResponseVo responseVo = null;
    try {
      responseVo = OpenAiClient.chatWithModel(GoogleGeminiModels.GEMINI_2_0_FLASH_EXP, "user", prompt);
    } catch (Exception e) {
      try {
        responseVo = OpenAiClient.chatWithModel(GoogleGeminiModels.GEMINI_2_0_FLASH_EXP, "user", prompt);
      } catch (Exception e2) {
        responseVo = OpenAiClient.chatWithModel(GoogleGeminiModels.GEMINI_2_0_FLASH_EXP, "user", prompt);
      }
    }
    return responseVo;
  }
}
