package com.example.end.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping("/")
  public String home() {
    return "home"; // Вернуть имя шаблона для домашней страницы (например, "home.html")
  }
}
