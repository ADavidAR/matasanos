package com.matasanos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recetas")
public class RecetaViewController {

    @GetMapping
    public String roles() { return "prescripctions.html"; }

}
