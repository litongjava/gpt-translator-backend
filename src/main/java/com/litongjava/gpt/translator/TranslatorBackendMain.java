package com.litongjava.gpt.translator;

import com.litongjava.annotation.AComponentScan;
import com.litongjava.hotswap.wrapper.tio.boot.TioApplicationWrapper;

@AComponentScan
public class TranslatorBackendMain {
  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    TioApplicationWrapper.run(TranslatorBackendMain.class, args);
    long end = System.currentTimeMillis();
    System.out.println((end - start) + "ms");
  }
}
