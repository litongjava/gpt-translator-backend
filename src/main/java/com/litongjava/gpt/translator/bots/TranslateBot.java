package com.litongjava.gpt.translator.bots;

import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.Update;

import com.litongjava.jfinal.aop.Aop;
import com.litongjava.telegram.utils.ChatType;
import com.litongjava.tio.utils.json.JacksonUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TranslateBot implements LongPollingSingleThreadUpdateConsumer {

  @Override
  public void consume(Update update) {
    String writeValueAsString = JacksonUtils.toJson(update);
    log.info("update:{}", writeValueAsString);
    // 判断是否是文本消息
    if (update.hasMessage() && update.getMessage().hasText()) {
      String receivedText = update.getMessage().getText();
      log.info("Received text message: {}", receivedText);
      if ("/get_chat_id".equals(receivedText)) {
        Aop.get(GetChatIdService.class).index(update);

      } else if ("/start".equals(receivedText)) {
        Aop.get(StartService.class).index(update);

      } else if ("/about".equals(receivedText)) {
        Aop.get(StartService.class).about(update);

      } else {
        if (update.getMessage().getChat().getType().equals(ChatType.chat_private)) {
          Aop.get(BotTranslateService.class).index(update);
        }
      }
    }
    if (update.hasChannelPost() && update.getChannelPost().hasText()) {
      String receivedText = update.getChannelPost().getText();
      log.info("Received text message: {}", receivedText);
      log.info("Received text message: {}", receivedText);
      if ("/get_chat_id".equals(receivedText)) {
        Aop.get(GetChatIdService.class).index(update);

      } else if ("/start".equals(receivedText)) {
        Aop.get(StartService.class).index(update);

      } else if ("/about".equals(receivedText)) {
        Aop.get(StartService.class).about(update);
      }
    }
  }
}