package com.example.end.controller;


import com.example.end.dto.MasterDto;
import com.example.end.service.MasterService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@Controller
@Data
public class MasterController {

  private final MasterService masterService;

  @Autowired
  public MasterController(MasterService masterService) {
    this.masterService = masterService;
  }

  @GetMapping("/master/{masterId}")
  public String masterProfile(@PathVariable Long masterId, Model model) {
    MasterDto masterDto = masterService.getMasterProfile(masterId);
    model.addAttribute("master", masterDto);
    return "master/profile";
  }


  @PostMapping
  public String updateMasterProfile(MasterDto masterDto) {
    // Логика для обновления профиля мастера
    Long masterId = masterDto.getId(); // Получите идентификатор мастера
    masterService.updateMasterProfile(masterId, masterDto);
    return "redirect:/master";
  }
}

