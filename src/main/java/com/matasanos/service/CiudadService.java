package com.matasanos.service;

import com.matasanos.model.Ciudad;
import com.matasanos.repo.CiudadRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CiudadService {
    CiudadRepo ciudadRepo;

    public CiudadService(CiudadRepo ciudadRepo) {
        this.ciudadRepo = ciudadRepo;
    }


    public List<Ciudad> listarCiudades() {
        return ciudadRepo.listarCiudades();
    }
}
