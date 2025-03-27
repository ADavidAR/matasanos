package com.matasanos.controller;

import com.matasanos.model.Usuario;
import com.matasanos.service.RolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    RolService rolService;

    public RolController(RolService rolServicio) {
        this.rolService = rolServicio;
    }

    @GetMapping
    public ResponseEntity<?> listarRoles() {
        return ResponseEntity.ok(rolService.listarRoles());
    }

    @PostMapping("/verificar/existencia/{nombreRol}")
    public boolean verficarExistenciaRol(@PathVariable String nombreRol) { return rolService.verficarExistenciaRol(nombreRol); }

    @PostMapping("/agregar/{nombreRol}")
    public boolean agregarRol(@PathVariable String nombreRol) {
        return rolService.agragarRol(nombreRol);
    }

    @PostMapping("/modificar")
    public boolean agregarRol(@RequestParam String nombreRol, @RequestParam int idRol) {
        return rolService.modificarRol(nombreRol, idRol);
    }

    @PostMapping("/verificar/eliminar/{idRol}")
    public ResponseEntity<?> verficarEliminar(@PathVariable int idRol) {

        List<String> usuarios = rolService.listarUsuariosConRol(idRol);
        Map<String, Object> msg = new HashMap<>();
        if(!usuarios.isEmpty()) {

            msg.put("error", true);

            StringBuilder sb = new StringBuilder();
            sb.append("No se pudo eliminar rol porque esta ligado a usuario(s):");
            for(String u : usuarios) {
                sb.append(String.format(" %s", u));
            }

            msg.put("msg", sb.toString());
            return ResponseEntity.ok(msg);
        }
        msg.put("error", false);

        return ResponseEntity.ok(msg);
    }
    @DeleteMapping("/eliminar/{idRol}")
    public ResponseEntity<?> eliminarRol(@PathVariable int idRol) {

        List<String> usuarios = rolService.listarUsuariosConRol(idRol);
        Map<String, Object> msg = new HashMap<>();
        if(!usuarios.isEmpty()) {

            msg.put("error", true);

            StringBuilder sb = new StringBuilder();
            sb.append("No se pudo eliminar rol porque esta ligado a usuario(s):");
            for(String u : usuarios) {
                sb.append(String.format(" %s", u));
            }

            msg.put("msg", sb.toString());
            return ResponseEntity.ok(msg);
        }
        msg.put("error", false);

        rolService.eliminarRol(idRol);

        return ResponseEntity.ok(msg);
    }
}
