package com.matasanos.controller;

import com.matasanos.model.Usuario;
import com.matasanos.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
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
        String usuario = requestBody.get("user");
        String pass = requestBody.get("pass");

        Map<String, Object> response = new HashMap<>();
        Usuario u = usuarioService.autenticarUsuario(usuario, pass);
        if( u == null ) {
            response.put("msg", "Usuario o contraseña incorrecto");
            return ResponseEntity.badRequest().body(response);
        }

        if(!u.getActivo()) {
            response.put("msg", "Usuario desactivado. Pongase en contacto con el administrador");
            return ResponseEntity.badRequest().body(response);
        }

        response.put("userData", u);

        return ResponseEntity.ok(response);
    }
}
