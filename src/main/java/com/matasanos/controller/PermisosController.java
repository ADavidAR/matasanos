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

    @GetMapping("/rol/f/{idRol}")
    public ResponseEntity<?> listarPermisosPorRol(@PathVariable int idRol) {
        List<RolPermisos> permisos = permisosService.listarPermisosPorRol(idRol);

        return ResponseEntity.ok(permisos);
    }
    @GetMapping("/rol/p/{idRol}")
    public ResponseEntity<?> listarPermisosDeRol(@PathVariable int idRol) {
        List<RolPermisos> permisos = permisosService.listarPermisosDeRol(idRol);

        return ResponseEntity.ok(permisos);
    }


    @PutMapping("/rol")
    public ResponseEntity<?> actualizarPermisosDeRol(@RequestBody List<RolPermisos> rps) {
        if(permisosService.actualizarPermisosDeRol(rps))
            return ResponseEntity.ok("");

        return ResponseEntity.badRequest().body("");
    }

}
