package com.matasanos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductoViewController {

    @GetMapping("/productos")
    public String viewCategories(@RequestParam("idCategoria") int idCategoria, @RequestParam("idDepartamento") int idDepartamento, Model model) {
        model.addAttribute("idDepartamento", idDepartamento);
        model.addAttribute("idCategoria", idCategoria);
        return "products.html"; }
}