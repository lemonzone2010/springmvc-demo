package com.example.springmvcdemo;

import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @version 1.0
 * @date 11/13/17
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SpringmvcDemoApplication.class, BaseCase.AppConfig.class})
@WebAppConfiguration
public abstract class BaseCase {

  protected Map<String, String> paramMap = new HashMap<>();

  static {
    System.setProperty("spring.profiles.active", "test-bjdx");
  }

  @Rule
  public OutputCapture outputCapture = new OutputCapture();
  @Value("${server.context-path}")
  protected String contextPath;


  @Autowired
  private WebApplicationContext context;

  protected MockMvc mockMvc;

  @Before
  public void setupMockMvc() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

  }


  @Configuration
  @PropertySources(
      {@PropertySource("classpath:application.properties")
      }
  )
  public static class AppConfig {

  }
}
