package com.matasanos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ventas")
public class VentaViewController {

    @GetMapping
    public String venta(){
        return "venta.html";
    }
}
