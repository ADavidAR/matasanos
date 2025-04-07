package com.matasanos.controller;

import com.matasanos.service.ColoniaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/colonias")
public class ColoniaController {
    ColoniaService coloniaService;

    public ColoniaController(ColoniaService coloniaService) {
        this.coloniaService = coloniaService;
    }

    @GetMapping("/{idCiudad}")
    public ResponseEntity<?> listarCiudades(@PathVariable int idCiudad) {
        return ResponseEntity.ok(coloniaService.listarColoniasPorCiudad(idCiudad));
    }
}
