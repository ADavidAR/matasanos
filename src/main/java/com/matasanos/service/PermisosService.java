package com.matasanos.service;

import com.matasanos.model.Rol;
import com.matasanos.model.RolPermisos;
import com.matasanos.repo.PermisoRepo;
import com.matasanos.repo.RolPermisosRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermisosService {

    PermisoRepo permisoRepo;
    RolPermisosRepo rolPermisosRepo;
    UsuarioService usuarioService;

    public PermisosService(PermisoRepo permisoRepo, RolPermisosRepo rolPermisosRepo, UsuarioService usuarioService) {
        this.permisoRepo = permisoRepo;
        this.rolPermisosRepo = rolPermisosRepo;
        this.usuarioService = usuarioService;
    }

    public List<RolPermisos> listarPermisosPorRol(int idRol) {
        return rolPermisosRepo.listarPermisosPorRol(idRol);
    }

    public List<RolPermisos> listarPermisosDeRol(int idRol) {
        return rolPermisosRepo.listarPermisosDeRol(idRol);
    }

    public boolean actualizarPermisosDeRol(List<RolPermisos> rps) {
        return rolPermisosRepo.actualizarPermisosDeRol(rps);
    }
}
