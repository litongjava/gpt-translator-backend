package com.litongjava.gpt.translator.controller;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import com.litongjava.annotation.Get;
import com.litongjava.annotation.RequestPath;
import com.litongjava.gpt.translator.services.DocumentTranslateService;
import com.litongjava.jfinal.aop.Aop;
import com.litongjava.model.body.RespBodyVo;
import com.litongjava.tio.http.common.HttpResponse;
import com.litongjava.tio.utils.thread.TioThreadUtils;

@RequestPath("/api/document")
public class ApiDocumentTranslateController {

  @Get("/translate/{documentId}")
  public RespBodyVo index(Long documentId) throws IOException, InterruptedException, ExecutionException {
    TioThreadUtils.submit(() -> {
      try {
        return Aop.get(DocumentTranslateService.class).index(documentId);
      } catch (Exception e) {
        e.printStackTrace();
      }
      return null;
    });
    return RespBodyVo.ok();
  }

  @Get("/export/{documentId}")
  public HttpResponse export(Long documentId) throws IOException, InterruptedException, ExecutionException {
    return Aop.get(DocumentTranslateService.class).export(documentId);
  }
  
  @Get("/export/src/{documentId}")
  public HttpResponse exportSrc(Long documentId) throws IOException, InterruptedException, ExecutionException {
    return Aop.get(DocumentTranslateService.class).exportSrc(documentId);
  }
}
