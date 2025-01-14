package com.litongjava.gpt.translator.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelegramMessage {
  private Long id;
  private Long fromId;
  private Long toId;
  private Integer sequence;
  private String messageText;
  private LocalDateTime messageDate;
  private String messageType;
  private String entities;
  private String fromUsername;
  private String fromFirstName;
  private String fromLastName;
  private String toUsername;
  private String toFirstName;
  private String toLastName;
  private LocalDateTime create_time;
}