package com.litongjava.gpt.translator.config;

import com.litongjava.annotation.AConfiguration;
import com.litongjava.annotation.Initialization;
import com.litongjava.gpt.translator.handler.OpenAiV1ProxyHandler;
import com.litongjava.gpt.translator.handler.TranslatorRequestHandler;
import com.litongjava.jfinal.aop.Aop;
import com.litongjava.llm.handler.ApiChatHandler;
import com.litongjava.tio.boot.admin.config.TioAdminControllerConfiguration;
import com.litongjava.tio.boot.admin.config.TioAdminDbConfiguration;
import com.litongjava.tio.boot.admin.config.TioAdminHandlerConfiguration;
import com.litongjava.tio.boot.admin.config.TioAdminInterceptorConfiguration;
import com.litongjava.tio.boot.admin.config.TioAdminSaTokenConfiguration;
import com.litongjava.tio.boot.admin.handler.SystemFileAwsS3Handler;
import com.litongjava.tio.boot.server.TioBootServer;
import com.litongjava.tio.http.server.router.HttpRequestRouter;

@AConfiguration
public class TranslateAdminAppConfig {

  @Initialization
  public void config() {
    String[] permitUrls = {
        //
        "/api/document/export/*", "/translate/text", "/telegram/webhook",
        //
        "/api/v1/telegram/sendMessage","/openai/v1/*" };
    // 配置数据库相关
    new TioAdminDbConfiguration().config();
    new TioAdminSaTokenConfiguration().config();
    new TioAdminInterceptorConfiguration(permitUrls).config();
    new TioAdminHandlerConfiguration().config();

    // 获取 HTTP 请求路由器
    HttpRequestRouter r = TioBootServer.me().getRequestRouter();
    if (r != null) {
      // 获取文件处理器，并添加文件上传和获取 URL 的接口
      SystemFileAwsS3Handler systemUploadHandler = Aop.get(SystemFileAwsS3Handler.class);
      r.add("/api/system/file/upload", systemUploadHandler::upload);
      r.add("/api/system/file/url", systemUploadHandler::getUrl);

      // 创建controller
      TranslatorRequestHandler trnslatorRequestHandler = Aop.get(TranslatorRequestHandler.class);

      // 添加action
      r.add("/translate/text", trnslatorRequestHandler::text);

      OpenAiV1ProxyHandler openaiV1ChatHandler = new OpenAiV1ProxyHandler();
      r.add("/openai/v1/models", openaiV1ChatHandler::models);
      r.add("/openai/v1/chat/completions", openaiV1ChatHandler::completions);
      
      ApiChatHandler apiChatHandler = Aop.get(ApiChatHandler.class);
      r.add("/api/v1/chat/recommend", apiChatHandler::recommend);
      r.add("/api/v1/chat/create", apiChatHandler::createSession);
      r.add("/api/v1/chat/list", apiChatHandler::listSession);
      r.add("/api/v1/chat/delete", apiChatHandler::deleteSession);
      r.add("/api/v1/chat/set/name", apiChatHandler::setSessionName);
      r.add("/api/v1/chat/like", apiChatHandler::like);
      r.add("/api/v1/chat/history", apiChatHandler::getChatHistory);
      r.add("/api/v1/chat/stop", apiChatHandler::stop);
      r.add("/api/v1/chat/send", apiChatHandler::send);
      
    }

    // 配置控制器
    new TioAdminControllerConfiguration().config();
  }
}
