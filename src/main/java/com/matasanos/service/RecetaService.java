package com.matasanos.service;

import com.matasanos.model.Receta;
import com.matasanos.repo.RecetaRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecetaService {

    RecetaRepo recetaRepo;

    public RecetaService(RecetaRepo recetaRepo) {
        this.recetaRepo = recetaRepo;
    }

    public List<Receta> listarRecetas() {
        return recetaRepo.listarRecetas();
    }

    public Receta obtenerRecetaPorId(int idReceta) {
        return recetaRepo.obtenerRecetaPorId(idReceta);
    }

    public void agregar(Receta receta) {
        recetaRepo.agregar(receta);
    }

    public void modificar(Receta receta) {
        recetaRepo.modificar(receta);
    }

    public boolean existe(int idReceta) {
        return recetaRepo.existe(idReceta);
    }

    public void eliminar(int idReceta) {
        recetaRepo.eliminar(idReceta);
    }
}
