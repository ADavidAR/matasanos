package com.matasanos.controller;

import com.matasanos.service.EmpleadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {
    EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping("/sinusuario")
    public ResponseEntity<?> listarEmpleadosSinUsuario() {
        return ResponseEntity.ok(empleadoService.listarEmpleadosSinUsuario());
    }
}
