package com.litongjava.gpt.translator.services;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.jfinal.template.Engine;
import com.jfinal.template.Template;
import com.litongjava.gpt.translator.config.EnjoyEngineConfig;

public class EnjoyEngineConfigTest {

  @Test
  public void test() {
    new EnjoyEngineConfig().config();
    Engine engine = Engine.use();
    Template template = engine.getTemplate("translator_prompt.txt");
    Map<String, String> values = new HashMap<>();

    values.put("source_lang", "Chinese");
    values.put("target_lang", "English");
    values.put("source_text", "良禽择木而栖");

    String renderToString = template.renderToString(values);
    System.out.println(renderToString);
  }
}
