package com.litongjava.gpt.translator.config;

import java.util.concurrent.TimeUnit;

import com.litongjava.annotation.ABean;
import com.litongjava.annotation.BeforeStartConfiguration;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;

@BeforeStartConfiguration
public class OkHttpClientConfig {

  @ABean(priority = 100)
  public OkHttpClient config() {
    Builder builder = new OkHttpClient().newBuilder();
    // 连接池
    builder.connectionPool(pool());
    // 连接超时
    builder.connectTimeout(300L, TimeUnit.SECONDS).readTimeout(300L, TimeUnit.SECONDS).build();
    return builder.build();
  }

  private ConnectionPool pool() {
    return new ConnectionPool(200, 5, TimeUnit.MINUTES);
  }
}
