package com.matasanos.controller;

import com.matasanos.model.Producto;
import com.matasanos.service.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("")
    public ResponseEntity<?> listarProductosDeSucursal(@RequestParam(required = false) Integer idSucursal) {

        List<Producto> productos;
        if(idSucursal != null) {
           productos = productoService.listarProductosDeSucursal(idSucursal);
        } else {
            productos = productoService.listarProductos();
        }

        return ( productos != null ) ? ResponseEntity.ok(productos) : ResponseEntity.ok(Collections.emptyMap());
    }

    //Eg: /api/productos/1?idSucursal=2
    @GetMapping("/{idProducto}")
    public ResponseEntity<?> obtenerProductoPorId( @PathVariable int idProducto, @RequestParam(required = false) Integer idSucursal) {

        Producto producto;
        if(idSucursal != null) {
            producto = productoService.obtenerProductoDeSucursal(idProducto, idSucursal);
        } else {
            producto = productoService.obtenerProducto(idProducto);
        }
        return ( producto != null ) ? ResponseEntity.ok(producto) : ResponseEntity.ok(Collections.emptyMap());
    }

    //Eg: /api/productos/busqueda?filtro=ibu&idSucursal=2
    @GetMapping("/busqueda")
    public ResponseEntity<?> listarProductosDeSucursal(@RequestParam String filtro, @RequestParam(required = false) Integer idSucursal) {

        List<Producto> productos;
        if(idSucursal != null) {
            productos = productoService.filtrarProductosDeSucursalPorNombre(filtro, idSucursal);
        } else {
            productos = productoService.filtrarProductosPorNombre(filtro);
        }

        return ( productos != null ) ? ResponseEntity.ok(productos) : ResponseEntity.ok(Collections.emptyMap());
    }

    //Eg: /api/productos/1?idCategoria=2
    @GetMapping("/sucursal/{idSucursal}")
    public ResponseEntity<?> listarProductosDeSucursalPorCategoria(@PathVariable int idSucursal, @RequestParam(required = false) Integer idCategoria) {

        List<Producto> productos;
        productos = productoService.listarProductosDeSucursalPorCategoria(idSucursal, idCategoria);
        return ( productos != null ) ? ResponseEntity.ok(productos) : ResponseEntity.ok(Collections.emptyMap());
    }

    @GetMapping("/busqueda_simplificada")
    public ResponseEntity<?> filtrarProductosDeSucursalPorNombreSimplificado(@RequestParam String filtro, @RequestParam(required = false) Integer idSucursal) {

        List<Producto> productos;
        productos = productoService.filtrarProductosDeSucursalPorNombreSimplificado(idSucursal, filtro);

        return ( productos != null ) ? ResponseEntity.ok(productos) : ResponseEntity.ok(Collections.emptyMap());
    }
    

}
