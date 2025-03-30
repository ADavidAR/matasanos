package com.matasanos.controller;

import com.matasanos.model.Categoria;
import com.matasanos.service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/{idDepartamento}")
    public ResponseEntity<?> listarCategorias(@PathVariable int idDepartamento) {

        List<Categoria> categoria;
        categoria = categoriaService.listarCategorias(idDepartamento);

        return (categoria != null && !categoria.isEmpty())
                ? ResponseEntity.ok(categoria)
                : ResponseEntity.noContent().build();
    }

}

