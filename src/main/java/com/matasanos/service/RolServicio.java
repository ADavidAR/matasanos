package com.matasanos.service;

import com.matasanos.model.Rol;
import com.matasanos.repo.RolRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServicio {

    RolRepo rolRepo;

    public RolServicio(RolRepo rolRepo) {
        this.rolRepo = rolRepo;
    }

    public List<Rol> listarRoles() {
        return rolRepo.listarRolesSimples();
    }
}
