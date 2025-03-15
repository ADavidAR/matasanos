package com.matasanos.controller;

import com.matasanos.model.Usuario;
import com.matasanos.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("")
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @PostMapping("/verificar")
    public ResponseEntity<?> obtenerUsuarioPorNombre(@RequestBody Map<String, String> requestBody) {
        String usuario = requestBody.get("usuario");
        String pass = requestBody.get("pass");

        Usuario u = usuarioService.autenticarUsuario(usuario, pass);

        return ( u != null ) ? ResponseEntity.ok(u) : ResponseEntity.ok(Collections.emptyMap());

    }
}
