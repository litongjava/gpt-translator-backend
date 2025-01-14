package com.litongjava.gpt.translator.bots;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import com.litongjava.telegram.can.TelegramClientCan;
import com.litongjava.telegram.utils.SendMessageUtils;

public class StartService {

  public void index(Update update) {
    Long chatId = update.getMessage().getChatId();
    String welcomeMessage = "🌐 **翻译机器人** 🌐\n\n 这个机器人使用 GPT 技术为您提供高质量的文本翻译服务。欢迎使用翻译机器人！\n\n";
    SendMessage markdown = SendMessageUtils.markdown(chatId, welcomeMessage);
    TelegramClientCan.execute(markdown);
  }
  
  public void about(Update update) {
    Long chatId = update.getMessage().getChatId();
    String aboutMessage = "**开发者:** Litong Java\n" + "**版本:** 1.0.0\n\n" + "感谢您使用本机器人！";
    SendMessage markdown = SendMessageUtils.markdown(chatId, aboutMessage);
    TelegramClientCan.execute(markdown);
  }

}
