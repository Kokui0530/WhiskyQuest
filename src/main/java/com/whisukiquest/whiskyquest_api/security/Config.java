package com.whisukiquest.whiskyquest_api.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config implements WebMvcConfigurer {
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedOrigins("http://whiskyquestfrontalb-2012089750.ap-northeast-1.elb.amazonaws.com")
        .allowedMethods("*")
        .allowedHeaders("*");
  }
}
