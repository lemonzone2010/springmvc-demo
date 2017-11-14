package com.example.springmvcdemo.controller.camel;

import java.util.List;
import org.springframework.web.method.annotation.InitBinderDataBinderFactory;
import org.springframework.web.method.support.InvocableHandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

/**
 *  下划线风格命名转换为驼峰
 * @version 1.0
 * @date 11/14/17
 */
public class SnakeToCamelRequestMappingHandlerAdapter extends RequestMappingHandlerAdapter {

  @Override
  protected InitBinderDataBinderFactory createDataBinderFactory(
      List<InvocableHandlerMethod> binderMethods)
      throws Exception {
    return new SnakeToCamelServletRequestDataBinderFactory(binderMethods,
        getWebBindingInitializer());
  }

}
