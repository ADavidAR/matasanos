package com.matasanos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cajas")
public class CajaViewController {

    @GetMapping
    public String roles() { return "checkouts.html"; }

}
