package com.litongjava.gpt.translator.controller;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.message.Message;

import com.litongjava.annotation.RequestPath;
import com.litongjava.model.body.RespBodyVo;
import com.litongjava.telegram.can.TelegramClientCan;
import com.litongjava.telegram.utils.SendMessageUtils;

@RequestPath("/api/v1/telegram")
public class TelegramController {

  public RespBodyVo sendMessage(Long chatId, String content) {
    SendMessage text = SendMessageUtils.text(chatId, content);
    try {
      Message execute = TelegramClientCan.execute(text);
      return RespBodyVo.ok(execute);
    } catch (Exception e) {
      return RespBodyVo.fail(e.getMessage());
    }
  }
}
