package com.matasanos.service;

import com.matasanos.model.RolPermiso;
import com.matasanos.repo.PermisoRepo;
import com.matasanos.repo.RolPermisoRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermisosService {

    PermisoRepo permisoRepo;
    RolPermisoRepo rolPermisoRepo;
    UsuarioService usuarioService;

    public PermisosService(PermisoRepo permisoRepo, RolPermisoRepo rolPermisoRepo, UsuarioService usuarioService) {
        this.permisoRepo = permisoRepo;
        this.rolPermisoRepo = rolPermisoRepo;
        this.usuarioService = usuarioService;
    }

    public List<RolPermiso> listarPermisosPorRol(int idRol) {
        return rolPermisoRepo.listarPermisosPorRol(idRol);
    }

    public List<RolPermiso> listarPermisosDeRol(int idRol) {
        return rolPermisoRepo.listarPermisosDeRol(idRol);
    }

    public boolean actualizarPermisosDeRol(List<RolPermiso> rps) {
        return rolPermisoRepo.actualizarPermisosDeRol(rps);
    }
}
