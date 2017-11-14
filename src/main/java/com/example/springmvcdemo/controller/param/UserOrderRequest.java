package com.example.springmvcdemo.controller.param;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 用户信息查询请求
 * @version 1.0
 * @date 11/11/17
 */
@EnableSnakeToCamel
@Data
public class UserOrderRequest {

  private String userName;

  private String goodsId;

  @Override
  public String toString(){
    return ToStringBuilder.reflectionToString(this);
  }
}
