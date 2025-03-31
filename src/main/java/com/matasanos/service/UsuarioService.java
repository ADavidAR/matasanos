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
        if(u != null && u.getIdUsuario() != idUsuario) return false;

        return false;
    }

    public boolean verificarCreacionNombre(String usuario, int idUsuario) {
        Usuario u = usuarioRepo.obtenerUsuario(usuario);
        if(u != null) return false;

        return false;
    }

    public boolean verficarEmpleado(int idEmpleado) {
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

        if(usuario.getContrasena() != null)
            usuarioRepo.modificarUsuario(usuario);
        else
            usuarioRepo.modificarUsuarioConContrasena(usuario);
    }

    public void agregarUsuario(Usuario usuario) {
        usuarioRepo.agregarUsuario(usuario);
    }
}
