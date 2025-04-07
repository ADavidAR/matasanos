package com.matasanos.service;

import com.matasanos.model.Usuario;
import com.matasanos.repo.EmpleadoRepo;
import com.matasanos.repo.UsuarioRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepo usuarioRepo;
    private final EmpleadoRepo empleadoRepo;

    public UsuarioService(UsuarioRepo usuarioRepo, EmpleadoRepo empleadoRepo) {
        this.usuarioRepo = usuarioRepo;
        this.empleadoRepo = empleadoRepo;
    }

    public Usuario autenticarUsuario(String usuario, String pass) {
        Usuario u = usuarioRepo.obtenerUsuario(usuario);
        if(u == null || !u.getContrasena().equals(pass)) return null;
        return usuarioRepo.obtenerUsuarioConPermisos(usuario);
    }
    public List<Usuario> listarUsuarios() {
        return usuarioRepo.listarUsuarios();
    }

    public boolean verificarModificacionNombre(String usuario, int idUsuario) {
        Usuario u = usuarioRepo.obtenerUsuario(usuario);
        return (u != null && u.getIdUsuario() != idUsuario);
    }

    public boolean verificarCreacionNombre(String usuario) {
        Usuario u = usuarioRepo.obtenerUsuario(usuario);
        return (u != null);
    }

    public boolean verificarEmpleado(int idEmpleado, int idUsuario) {
        return empleadoRepo.tieneUsuarioAsignado(idEmpleado, idUsuario);
    }

    public boolean verificarEmpleado(int idEmpleado) {
        return empleadoRepo.tieneUsuarioAsignado(idEmpleado);
    }
    public Usuario obtenerUsuarioEmpleadoRol(int idUsuario) {
        return usuarioRepo.obtenerUsuarioEmpleadoRol(idUsuario);
    }

    public boolean haRealizadoModificaciones(int idUsuario) {
        return usuarioRepo.haRealizadoModificaciones(idUsuario);
    }

    public boolean existe(int idUsuario) {
        return usuarioRepo.existeUsuario(idUsuario);
    }

    public void eliminarUsuario(int idUsuario) {
        usuarioRepo.eliminar(idUsuario);
    }

    public void modificarUsuario(Usuario usuario) {

        usuarioRepo.modificarUsuario(usuario);
        if(usuario.getContrasena() != null)
            usuarioRepo.modificarContrasena(usuario);
    }

    public void agregarUsuario(Usuario usuario) {
        usuarioRepo.agregarUsuario(usuario);
    }
}
