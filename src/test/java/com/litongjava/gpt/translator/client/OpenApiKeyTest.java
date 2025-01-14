package com.litongjava.gpt.translator.client;

import org.junit.Test;

import com.litongjava.gemini.GoogleGeminiModels;
import com.litongjava.openai.chat.OpenAiChatResponseVo;
import com.litongjava.openai.client.OpenAiClient;
import com.litongjava.tio.utils.environment.EnvUtils;
import com.litongjava.tio.utils.json.JsonUtils;

public class OpenApiKeyTest {

  @Test
  public void getOpenApiKey() {
    EnvUtils.load();
    String string = EnvUtils.get("OPENAI_API_KEY");
    System.out.println(string);
  }

  @Test
  public void tetChat() {
    EnvUtils.load();
    OpenAiChatResponseVo chatResponse = OpenAiClient.chatWithModel(GoogleGeminiModels.GEMINI_2_0_FLASH_EXP, "user", "Hi");
    System.out.println(JsonUtils.toJson(chatResponse));
  }
}
