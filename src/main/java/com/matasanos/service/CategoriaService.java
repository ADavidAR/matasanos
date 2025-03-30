package com.matasanos.service;

import com.matasanos.model.Categoria;
import com.matasanos.repo.CategoriaRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepo categoriaRepo;

    public CategoriaService(CategoriaRepo categoriaRepo) {
        this.categoriaRepo = categoriaRepo;
    }

    public List<Categoria> listarCategorias(int idDepartamento) {
        return categoriaRepo.listarCategorias(idDepartamento);
    }
}