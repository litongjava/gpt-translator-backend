package com.litongjava.gpt.translator.bots;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.chat.Chat;

import com.litongjava.gpt.translator.services.TranslatorService;
import com.litongjava.gpt.translator.vo.TranslatorTextVo;
import com.litongjava.jfinal.aop.Aop;
import com.litongjava.telegram.can.TelegramClientCan;
import com.litongjava.telegram.utils.SendMessageUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BotTranslateService {

  public void index(Update update) {
    String text = update.getMessage().getText();
    Chat chat = update.getMessage().getChat();
    Long chatIdLong = chat.getId();
    // 根据文本内容设置源语言和目标语言
    String srcLang;
    String destLang;

    if (containsChinese(text)) {
      srcLang = "Chinese";
      destLang = "English";
    } else {
      srcLang = "English";
      destLang = "Chinese";
    }

    // 创建翻译请求对象
    TranslatorTextVo translatorTextVo = new TranslatorTextVo();
    translatorTextVo.setSrcText(text);
    translatorTextVo.setSrcLang(srcLang);
    translatorTextVo.setDestLang(destLang);
    String responseText;
    try {
      // 调用翻译服务
      responseText = Aop.get(TranslatorService.class).translate(chatIdLong.toString(), translatorTextVo);
    } catch (Exception e) {
      log.error("Exception", e);
      responseText = "Exception: " + e.getMessage();
    }
    SendMessage markdown = SendMessageUtils.markdown(chatIdLong, responseText);
    TelegramClientCan.execute(markdown);
  }

  /**
   * 判断文本中是否包含中文字符
   *
   * @param text 输入的文本
   * @return 如果包含中文字符则返回 true，否则返回 false
   */
  private boolean containsChinese(String text) {
    if (text == null || text.isEmpty()) {
      return false;
    }
    // 使用正则表达式检查是否包含中文字符
    return text.matches(".*[\\u4e00-\\u9fa5]+.*");
  }

}
