package com.matasanos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matasanos.dto.ProductoCantidadDto;
import com.matasanos.model.Categoria;
import com.matasanos.model.Departamento;
import com.matasanos.model.Producto;
import com.matasanos.service.VentaService;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping("/{id}")
    public List<Producto> listarProductosDeSucursal(@PathVariable int id){
        return this.ventaService.listarProductosDeSucursal(id);
    }

    @GetMapping("/departamentos")
    public List<Departamento> listarDepartamentos(){
        return this.ventaService.listarDepartamentos();
    }

    @GetMapping("/categorias")  
    public List<Categoria> listarCategorias(){
        return this.ventaService.listarCategorias();
    }

    @PostMapping("/actualizar/inventario")
    public ResponseEntity<String> procesarOrden(@RequestBody List<ProductoCantidadDto> carrito) {
        System.out.println("Carrito recibido: " + carrito);
        this.ventaService.procesarOrden(carrito);

        return ResponseEntity.ok("Orden procesada correctamente");
    }
}
