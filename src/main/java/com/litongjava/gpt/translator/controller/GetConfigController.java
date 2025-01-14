package com.litongjava.gpt.translator.controller;

import java.util.HashMap;
import java.util.Map;

import com.litongjava.annotation.RequestPath;
import com.litongjava.model.body.RespBodyVo;
import com.litongjava.tio.utils.environment.EnvUtils;

@RequestPath("/get/config")
public class GetConfigController {

  public RespBodyVo index() {
    String apiKey = EnvUtils.get("OPENAI_API_KEY");
    Map<String, String> envs = new HashMap<>();
    envs.put("OPENAI_API_KEY", apiKey);
    return RespBodyVo.ok(envs);

  }

}
