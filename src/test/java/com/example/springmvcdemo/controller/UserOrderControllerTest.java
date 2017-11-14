package com.example.springmvcdemo.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.springmvcdemo.BaseCase;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;

/**
 * @version 1.0
 * @date 11/14/17
 */
public class UserOrderControllerTest extends BaseCase{

  Map<String, String> map = new HashMap<>();

  @Before
  public void init() {
    map.putAll(paramMap);
    map.put("user_name","kay");
    map.put("goods_id","test_user_id");
  }


  @Test
  public void execute() throws Exception {
    final LinkedMultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
    multiValueMap.setAll(map);
    mockMvc.perform(get(contextPath +  "/api/v1/order")
        .contextPath(contextPath)
        .params(multiValueMap)
    )
        .andDo(print())
        .andExpect(status().isOk())
      .andExpect(jsonPath("$.user_name").value("kay"))

    ;

  }

}