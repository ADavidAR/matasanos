package com.matasanos.service;

import com.matasanos.model.Colonia;
import com.matasanos.repo.ColoniaRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColoniaService {
    ColoniaRepo coloniaRepo;

    public ColoniaService(ColoniaRepo coloniaRepo) {
        this.coloniaRepo = coloniaRepo;
    }


    public List<Colonia> listarColoniasPorCiudad(int idCiudad) {
        return coloniaRepo.listarColoniasPorCiudad(idCiudad);
    }
}
