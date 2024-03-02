package com.example.end.controller;


import com.example.end.dto.MasterDto;
import com.example.end.service.MasterService;
import io.swagger.models.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MasterController {

  private MasterService masterService;

  @Autowired
  public MasterController(MasterService masterService) {
    this.masterService = masterService;
  }

  @GetMapping("/master")
  public String masterProfile(Model model) {
    // Логика для отображения профиля мастера
    return "master/profile"; // Замените на фактический путь к шаблону
  }

  @PostMapping("/master")
  public String updateMasterProfile(MasterDto masterDto) {
    // Логика для обновления профиля мастера
    return "redirect:/master";
  }
}

