package com.litongjava.gpt.translator.services;

import org.junit.Test;

import com.litongjava.gpt.translator.config.EnjoyEngineConfig;
import com.litongjava.gpt.translator.config.TranslateAdminAppConfig;
import com.litongjava.gpt.translator.vo.TranslatorTextVo;
import com.litongjava.openai.chat.OpenAiChatResponseVo;
import com.litongjava.tio.boot.testing.TioBootTest;
import com.litongjava.tio.utils.json.FastJson2Utils;

public class TranslatorServiceTest {

  @Test
  public void test() {
    TioBootTest.runWith(EnjoyEngineConfig.class);
    TranslatorService translatorService = new TranslatorService();
    TranslatorTextVo vo = new TranslatorTextVo();
    vo.setSrcText("今天的工作完成了吗?");
    vo.setSrcLang("Chinese");
    vo.setDestLang("English");
    OpenAiChatResponseVo repsonseVo = translatorService.translate(vo);
    String jsonString = FastJson2Utils.toJson(repsonseVo);
    String content = repsonseVo.getChoices().get(0).getMessage().getContent();
    System.out.println(content);
  }

  @Test
  public void testTranslateWithCache() {
    TioBootTest.runWith(TranslateAdminAppConfig.class, EnjoyEngineConfig.class);
    TranslatorTextVo vo = new TranslatorTextVo();
    vo.setSrcText("今天的工作完成了吗?");
    vo.setSrcLang("Chinese");
    vo.setDestLang("English");
    TranslatorService translatorService = new TranslatorService();
    String translate = translatorService.translate("1", vo);
    System.out.println(translate);
  }

}
