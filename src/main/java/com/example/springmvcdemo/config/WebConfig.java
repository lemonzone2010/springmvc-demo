package com.example.springmvcdemo.config;

import com.example.springmvcdemo.controller.camel.SnakeToCamelRequestMappingHandlerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

/**
 * web 配置
 * @version 1.0
 * @date 11/11/17
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

  @Override
  protected RequestMappingHandlerAdapter createRequestMappingHandlerAdapter() {
    return new SnakeToCamelRequestMappingHandlerAdapter();
  }

}
