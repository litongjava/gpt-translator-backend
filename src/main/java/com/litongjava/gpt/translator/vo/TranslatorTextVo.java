package com.litongjava.gpt.translator.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TranslatorTextVo {
  // 今天的任务你完成了吗?,Chinese,English
  private String srcText, srcLang, destLang;
}
