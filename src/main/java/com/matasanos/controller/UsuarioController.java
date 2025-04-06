package com.matasanos.controller;

import com.matasanos.model.Usuario;
import com.matasanos.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    public ResponseEntity<?> obtenerUsuario(@RequestBody Map<String, String> requestBody) {
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

    @PostMapping("/verificar/edicion")
    public ResponseEntity<?> validarModificacion(@RequestBody Usuario usuario) {

        Map<String, Object> response = new HashMap<>();
        if(usuarioService.verificarModificacionNombre(usuario.getUsuario(), usuario.getIdUsuario())) {
            response.put("msg", "usuario existente");
            return ResponseEntity.badRequest().body(response);
        }

        if(usuarioService.verificarEmpleado(usuario.getEmpleado().getIdEmpleado(), usuario.getIdUsuario())) {
            response.put("msg", "empleado con usuario");
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/verificar/crear")
    public ResponseEntity<?> validarCreacion(@RequestBody Usuario usuario) {

        Map<String, Object> response = new HashMap<>();
        if(usuarioService.verificarCreacionNombre(usuario.getUsuario())) {
            response.put("msg", "usuario existente");
            return ResponseEntity.badRequest().body(response);
        }

        if(usuarioService.verificarEmpleado(usuario.getEmpleado().getIdEmpleado())) {
            response.put("msg", "empleado con usuario");
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<?> obtenerUsuarioEmpleadoRol(@PathVariable int idUsuario) {
        Usuario u = usuarioService.obtenerUsuarioEmpleadoRol(idUsuario);

        return ( u != null ) ? ResponseEntity.ok(u) : ResponseEntity.ok(Collections.emptyMap());
    }

    @GetMapping("/verificar/eliminar/{idUsuario}")
    public ResponseEntity<?> verificarEliminar(@PathVariable int idUsuario) {
        Map<String, Object> response = new HashMap<>();

        if(usuarioService.haRealizadoModificaciones(idUsuario)) {
            response.put("msg", "usuario en uso");
            return ResponseEntity.badRequest().body(response);
        }

        if(!usuarioService.existe(idUsuario)) {
            response.put("msg", "usuario inexistente");
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/eliminar/{idUsuario}")
    public ResponseEntity<?> eliminar(@PathVariable int idUsuario) {
        usuarioService.eliminarUsuario(idUsuario);
        return ResponseEntity.ok(Collections.emptyMap());
    }

    @PostMapping("/modificar")
    public void modificar(@RequestBody Usuario usuario) {
        usuarioService.modificarUsuario(usuario);
    }

    @PostMapping("/agregar")
    public void agregar(@RequestBody Usuario usuario) {
        usuarioService.agregarUsuario(usuario);
    }
}
