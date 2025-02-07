package com.litongjava.gpt.translator.config;

import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import com.litongjava.annotation.Initialization;
import com.litongjava.gpt.translator.bots.TranslateBot;
import com.litongjava.hook.HookCan;
import com.litongjava.telegram.can.TelegramClientCan;
import com.litongjava.tio.utils.environment.EnvUtils;

//@AConfiguration
public class TelegramBotConfig {

  @Initialization
  public void config() {
    // 在此填写您的 Bot Token
    String botAuthToken = EnvUtils.getStr("telegram.bot.auth.token");
    
    if(botAuthToken== null) {
      return;
    }

    TelegramBotsLongPollingApplication botsApplication = configBot(botAuthToken);
    configClient(botAuthToken);

    // 在应用关闭时调用botsApplication的close方法
    HookCan.me().addDestroyMethod(() -> {
      try {
        botsApplication.stop();
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
  }

  private TelegramBotsLongPollingApplication configBot(String botAuthToken) {
    // 创建TelegramBotsLongPollingApplication实例，用于管理长轮询Bot的注册与启动
    TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication();

    try {
      // 注册自定义Bot
      botsApplication.registerBot(botAuthToken, new TranslateBot());
    } catch (TelegramApiException e) {
      e.printStackTrace();
    }
    return botsApplication;
  }

  private void configClient(String botAuthToken) {
    // 创建TelegramClient实例（使用OkHttp实现）
    TelegramClient telegramClient = new OkHttpTelegramClient(botAuthToken);
    TelegramClientCan.main = telegramClient;
  }
}