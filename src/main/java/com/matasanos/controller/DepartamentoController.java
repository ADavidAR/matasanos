package com.matasanos.controller;

import com.matasanos.model.Departamento;

import com.matasanos.service.DepartamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {

    private DepartamentoService departamentoService;

    public DepartamentoController(DepartamentoService departamentoService) { this.departamentoService = departamentoService; }

    @GetMapping("")
    public ResponseEntity<?> listarDepartamentos() {

        List<Departamento> departamentos;
        departamentos = departamentoService.listarDepartamentos();

        return ( departamentos != null ) ? ResponseEntity.ok(departamentos) : ResponseEntity.ok(Collections.emptyMap());
    }

    @PostMapping
    public ResponseEntity<String> guardarDepartamento(@RequestBody Departamento departamento) {
        departamentoService.guardarNuevoDepartamento(departamento);
        return ResponseEntity.ok("Departamento guardado correctamente");
    }

}