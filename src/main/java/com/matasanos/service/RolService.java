package com.matasanos.service;

import com.matasanos.model.Rol;
import com.matasanos.repo.RolRepo;
import com.matasanos.repo.UsuarioRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolService {

    RolRepo rolRepo;
    UsuarioRepo usuarioRepo;

    public RolService(RolRepo rolRepo, UsuarioRepo usuarioRepo) {
        this.rolRepo = rolRepo;
        this.usuarioRepo = usuarioRepo;
    }

    public List<Rol> listarRoles() {
        return rolRepo.listarRolesSimples();
    }

    public boolean verficarExistenciaRol(String nombreRol) {
        return rolRepo.existeRol(nombreRol);
    }

    public boolean agragarRol(String nombreRol) {
        return rolRepo.agregarRol(nombreRol);
    }

    public boolean modificarRol(String nombreRol, int idRol) {
        return rolRepo.modificarRol(nombreRol, idRol);
    }

    public boolean eliminarRol(int idRol) {
        return rolRepo.eliminarRol(idRol);
    }

    public List<String> listarUsuariosConRol(int idRol) {
        return usuarioRepo.listarUsuariosConRol(idRol);
    }
}
