package com.litongjava.gpt.translator.handler;

import com.litongjava.gpt.translator.services.TranslatorService;
import com.litongjava.gpt.translator.vo.TranslatorTextVo;
import com.litongjava.jfinal.aop.Aop;
import com.litongjava.model.body.RespBodyVo;
import com.litongjava.openai.chat.OpenAiChatResponseVo;
import com.litongjava.tio.boot.http.TioRequestContext;
import com.litongjava.tio.http.common.HttpRequest;
import com.litongjava.tio.http.common.HttpResponse;
import com.litongjava.tio.http.server.model.HttpCors;
import com.litongjava.tio.http.server.util.CORSUtils;
import com.litongjava.tio.utils.json.FastJson2Utils;

public class TranslatorRequestHandler {

  public HttpResponse text(HttpRequest httpRequest) {
    HttpResponse response = TioRequestContext.getResponse();
    CORSUtils.enableCORS(response, new HttpCors());

    // TranslatorTextVo
    String bodyString = httpRequest.getBodyString();
    TranslatorTextVo translatorTextVo = FastJson2Utils.parse(bodyString, TranslatorTextVo.class);
    RespBodyVo respVo;
    try {
      OpenAiChatResponseVo text = Aop.get(TranslatorService.class).translate(translatorTextVo);
      respVo = RespBodyVo.ok(text.getChoices().get(0).getMessage().getContent());
    } catch (Exception e) {
      e.printStackTrace();
      respVo = RespBodyVo.fail(e.getMessage());
    }

    return response.setJson(respVo);

  }

}
