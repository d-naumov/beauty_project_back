package com.example.end.controller;


import com.example.end.service.MasterService;
import com.example.end.service.interfaces.CategoryService;
import com.example.end.service.interfaces.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private MasterService masterService;

}