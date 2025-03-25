package com.matasanos.controller;

import com.matasanos.model.Rol;
import com.matasanos.model.RolPermisos;
import com.matasanos.service.PermisosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permisos")
public class PermisosController {

    PermisosService permisosService;

    public PermisosController(PermisosService permisosService) {
        this.permisosService = permisosService;
    }

    @PostMapping("/rol")
    public ResponseEntity<?> listarPermisosDeRol(@RequestBody Rol rol) {
        List<RolPermisos> permisos = permisosService.listarPermisosDeRol(rol);

        return ResponseEntity.ok(permisos);

    }

}
