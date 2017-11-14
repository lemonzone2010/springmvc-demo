package com.example.springmvcdemo.controller.camel;

import com.example.springmvcdemo.controller.param.EnableSnakeToCamel;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.ServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;

/**
 * 开启 入参 下划线风格命名转换为驼峰
 *
 */
public class SnakeToCamelRequestDataBinder extends ExtendedServletRequestDataBinder {

  private Map<Object, Boolean> cacheMap = new ConcurrentHashMap<>();

  public SnakeToCamelRequestDataBinder(Object target, String objectName) {
    super(target, objectName);
  }

  @Override
  protected void addBindValues(MutablePropertyValues mpvs, ServletRequest request) {
    super.addBindValues(mpvs, request);

    final Class<?> targetClazz = getTarget().getClass();
    Boolean enableSnakeToCamel = cacheMap.get(targetClazz);

    if (enableSnakeToCamel == null) {
      enableSnakeToCamel = this.parse(targetClazz);
      cacheMap.put(targetClazz, enableSnakeToCamel);
    }
    if (!enableSnakeToCamel) {
      return;
    }

    List<PropertyValue> camelPropertyValues = new ArrayList<>();
    for (PropertyValue propertyValue : mpvs.getPropertyValueList()) {
      final String name = propertyValue.getName();
      String camel = this.toCamel(name);
      if (!name.equals(camel)) {
        camelPropertyValues.add(new PropertyValue(camel, propertyValue.getValue()));
      }
    }

    camelPropertyValues.forEach(p -> mpvs.addPropertyValue(p));
  }

  /**
   * 下划线风格命名转换为驼峰
   * @param name 下划线风格
   * @return 驼峰
   */
  private String toCamel(String name) {
    StringBuilder sb = new StringBuilder();
    final String[] strings = StringUtils.split(name, "_");
    if (strings.length == 1) {
      return name;
    }
    for (int i = 0; i < strings.length; i++) {
      String s = strings[i];
      if (i == 0) {
        sb.append(s);
        continue;
      }
      sb.append(Character.toUpperCase(s.charAt(0)));
      if (s.length() > 1) {
        sb.append(s.substring(1, s.length()));
      }
    }
    return sb.toString();
  }

  /**
   * 当前入参是否已经开启
   * @param clazz
   * @return
   */
  private boolean parse(Class clazz) {
    Class currentClazz = clazz;
    while (currentClazz != Object.class && !currentClazz.isInterface()) {
      final Annotation annotation = currentClazz.getAnnotation(EnableSnakeToCamel.class);
      if (annotation != null) {
        return true;
      }
      currentClazz = currentClazz.getSuperclass();
    }
    return false;
  }


}