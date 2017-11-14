package com.example.springmvcdemo.controller;


import com.example.springmvcdemo.controller.param.UserOrderRequest;
import com.example.springmvcdemo.controller.param.UserOrderResponse;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 下单,生成订单
 *
 * @version 1.0
 * @date 11/11/17
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/v1")
public class UserOrderController {

  /**
   * 统一验证
   *
   * @param request 　请求
   * @param bindingResult 　验证结果
   */
  @RequestMapping(value = "/order", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public UserOrderResponse execute(@Valid UserOrderRequest request, BindingResult bindingResult) {
    log.info("request:{}", request);
    UserOrderResponse response = new UserOrderResponse();
    response.setUserName(request.getUserName());
    response.setOrderCode("1111111");
    return response;
  }

}
