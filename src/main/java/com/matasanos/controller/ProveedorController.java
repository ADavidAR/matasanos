package com.matasanos.controller;

import com.matasanos.model.Proveedor;
import com.matasanos.service.ProveedorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    private ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService) { this.proveedorService = proveedorService; }

    @GetMapping("")
    public ResponseEntity<?> listarProveedores() {

        List<Proveedor> proveedores;
        proveedores = proveedorService.listarProveedores();

        return ( proveedores != null ) ? ResponseEntity.ok(proveedores) : ResponseEntity.ok(Collections.emptyMap());
    }
}
