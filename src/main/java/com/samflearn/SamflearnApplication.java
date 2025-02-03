package com.samflearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SamflearnApplication {

  public static void main(String[] args) {
    SpringApplication.run(SamflearnApplication.class, args);
  }

}
