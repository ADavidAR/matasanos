package com.matasanos.service;

import com.matasanos.model.Producto;
import com.matasanos.model.Usuario;
import com.matasanos.repo.UsuarioRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepo usuarioRepo;

    public UsuarioService(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    public Usuario autenticarUsuario(String usuario, String pass) {
        Usuario u = usuarioRepo.obtenerUsuarioConPermisos(usuario);
        if(u == null || !u.getContrasena().equals(pass)) return null;
        return u;
    }
    public List<Usuario> listarUsuarios() {
        return usuarioRepo.listarUsuarios();
    }
}
