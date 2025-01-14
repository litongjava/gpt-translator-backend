package com.litongjava.gpt.translator.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;

import org.junit.Test;

import com.litongjava.gpt.translator.config.TranslateAdminAppConfig;
import com.litongjava.jfinal.aop.Aop;
import com.litongjava.tio.boot.testing.TioBootTest;
import com.litongjava.tio.utils.environment.EnvUtils;

public class PdfDocumentServiceTest {

  @Test
  public void test() {

    TioBootTest.runWith(TranslateAdminAppConfig.class);
    
    Path path = Paths.get("F:\\document\\项目资料总结\\13_项目-imaginix\\rumi\\02_文档向量", "50 Practice Questions to prepare for Exam 1 v2 final.pdf");
    byte[] fileBytes;
    try {
      fileBytes = Files.readAllBytes(path);
    } catch (IOException e1) {
      e1.printStackTrace();
      return;
    }
    try {
      String openaiApiKey = EnvUtils.get("OPENAI_API_KEY");
      String markdown = Aop.get(PdfDocumentService.class).toMarkdown(openaiApiKey, fileBytes);
      System.out.println(markdown);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }
}
