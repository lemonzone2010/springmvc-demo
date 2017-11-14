package com.example.springmvcdemo.controller.param;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 用户信息查询响应
 *
 * @version 1.0
 * @date 11/11/17
 */
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserOrderResponse {

  /**
   * 订单号
   */
  private String orderCode;
  /**
   * 订单号
   */
  private String userName;
  @Override
  public String toString(){
    return ToStringBuilder.reflectionToString(this);
  }
}
