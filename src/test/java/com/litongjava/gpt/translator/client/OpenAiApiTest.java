package com.litongjava.gpt.translator.client;

import org.junit.Test;

import com.litongjava.tio.utils.environment.EnvUtils;

public class OpenAiApiTest {
  @Test
  public void test01() {
    String serverUrl = EnvUtils.get("OPENAI_API_URL");
    System.out.println(serverUrl);
  }
}
