package com.matasanos.controller;

import com.matasanos.service.CompraService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/empleados")
public class ContratacionController {
    @GetMapping
    public String empleado(){return "employees.html";}




}
