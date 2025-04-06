package com.matasanos.controller;

import com.matasanos.service.CiudadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ciudades")
public class CiudadController {
    CiudadService ciudadService;

    public CiudadController(CiudadService ciudadService) {
        this.ciudadService = ciudadService;
    }

    @GetMapping("")
    public ResponseEntity<?> listarCiudades() {
        return ResponseEntity.ok(ciudadService.listarCiudades());
    }
}
