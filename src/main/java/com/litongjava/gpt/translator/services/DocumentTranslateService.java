package com.litongjava.gpt.translator.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.postgresql.util.PGobject;

import com.alibaba.fastjson2.JSON;
import com.litongjava.db.activerecord.Db;
import com.litongjava.db.activerecord.Row;
import com.litongjava.gpt.translator.constant.TableNames;
import com.litongjava.gpt.translator.vo.TranslatorTextVo;
import com.litongjava.jfinal.aop.Aop;
import com.litongjava.model.body.RespBodyVo;
import com.litongjava.openai.chat.OpenAiChatResponseVo;
import com.litongjava.tio.boot.admin.vo.UploadResultVo;
import com.litongjava.tio.boot.http.TioRequestContext;
import com.litongjava.tio.http.common.HttpResponse;
import com.litongjava.tio.utils.http.HttpDownloadUtils;
import com.litongjava.tio.utils.hutool.FilenameUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DocumentTranslateService {

  public RespBodyVo index(Long documentId) throws IOException, InterruptedException, ExecutionException {
    PGobject pgObject = Db.queryPGobjectById(TableNames.max_kb_document_translate, "files", documentId);
    UploadResultVo uploadResultVo = JSON.parseArray(pgObject.getValue(), UploadResultVo.class).get(0);

    String url = uploadResultVo.getUrl();
    ByteArrayOutputStream stream = HttpDownloadUtils.download(url, null);
    byte[] byteArray = stream.toByteArray();

    String markdown = Aop.get(PdfDocumentService.class).toMarkdown(byteArray);
    Db.update(TableNames.max_kb_document_translate, Row.by("id", documentId).set("src_content", markdown));

    TranslatorService translatorService = Aop.get(TranslatorService.class);
    TranslatorTextVo vo = new TranslatorTextVo();

    Row record = Db.findFirst(TableNames.max_kb_document_translate, "src_lang,dst_lang", Row.by("id", documentId));
    vo.setSrcText(markdown);
    vo.setSrcLang(record.getStr("src_lang"));
    vo.setDestLang(record.getStr("dst_lang"));

    OpenAiChatResponseVo repsonseVo = translatorService.translate(vo);
    String content = repsonseVo.getChoices().get(0).getMessage().getContent();
    System.out.println(content);
    Db.update(TableNames.max_kb_document_translate, Row.by("id", documentId).set("dst_content", content));
    return RespBodyVo.ok();
  }

  public HttpResponse export(Long documentId) {
    Row record = Db.findFirst(TableNames.max_kb_document_translate, "files,dst_lang,dst_content", Row.by("id", documentId));

    String filesJson = record.getStr("files");
    UploadResultVo uploadResultVo = JSON.parseArray(filesJson, UploadResultVo.class).get(0);
    String dst_lang = record.getStr("dst_lang");
    String content = record.getStr("dst_content");

    String originFilename = uploadResultVo.getName();
    String extName = "md";
    String baseName = FilenameUtils.getBaseName(originFilename);
    String downloadFilename = baseName + "_" + dst_lang + "." + extName;

    log.info("filename:{}", downloadFilename);

    String contentType = "text/markdown; charset=utf-8";
    HttpResponse httpResponse = TioRequestContext.getResponse();
    httpResponse.setContentType(contentType);
    httpResponse.setHeader("Content-Disposition", "attachment; filename=\"" + downloadFilename + "\"");
    httpResponse.setString(content);

    return httpResponse;
  }

  public HttpResponse exportSrc(Long documentId) {
    Row record = Db.findFirst(TableNames.max_kb_document_translate, "files,src_lang,src_content", Row.by("id", documentId));

    String filesJson = record.getStr("files");
    UploadResultVo uploadResultVo = JSON.parseArray(filesJson, UploadResultVo.class).get(0);
    String srclang = record.getStr("src_lang");
    String content = record.getStr("src_content");

    String originFilename = uploadResultVo.getName();
    String extName = "md";
    String baseName = FilenameUtils.getBaseName(originFilename);
    String downloadFilename = baseName + "_" + srclang + "." + extName;

    log.info("filename:{}", downloadFilename);

    String contentType = "text/markdown; charset=utf-8";
    HttpResponse httpResponse = TioRequestContext.getResponse();
    httpResponse.setContentType(contentType);
    httpResponse.setHeader("Content-Disposition", "attachment; filename=\"" + downloadFilename + "\"");
    httpResponse.setString(content);

    return httpResponse;
  }

}
