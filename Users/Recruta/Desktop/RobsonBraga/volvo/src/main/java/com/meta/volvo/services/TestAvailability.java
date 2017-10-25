package com.meta.volvo.services;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestAvailability {

  @RequestMapping("/rest/test")
  public String test() {
    return "Greetings. It is working!";
  }
}
