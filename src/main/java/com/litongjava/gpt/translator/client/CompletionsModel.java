package com.litongjava.gpt.translator.client;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompletionsModel {
  private String model;
  private List<ChatgptMessage> messages;
}
