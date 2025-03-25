package com.matasanos.controller;

import com.matasanos.model.Rol;
import com.matasanos.service.RolServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    RolServicio rolServicio;

    public RolController(RolServicio rolServicio) {
        this.rolServicio = rolServicio;
    }

    @GetMapping
    public ResponseEntity<?> listarRoles() {
        return ResponseEntity.ok(rolServicio.listarRoles());
    }
}
