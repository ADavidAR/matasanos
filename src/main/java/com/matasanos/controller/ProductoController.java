package com.matasanos.controller;

import com.matasanos.model.FichaInventario;
import com.matasanos.model.Producto;
import com.matasanos.service.ProductoService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> filtrarProductosDeSucursalPorNombreSimplificado(@RequestParam String filtro) {

        List<Producto> productos;
        productos = productoService.filtrarProductosDeSucursalPorNombreSimplificado(filtro);

        return ( productos != null ) ? ResponseEntity.ok(productos) : ResponseEntity.ok(Collections.emptyMap());
    }

    @GetMapping("/reportes/{idProducto}/{idSucursal}")
    public ResponseEntity<?> listarReportesProductoSucursal(@PathVariable int idProducto, @PathVariable int idSucursal) {

        List<FichaInventario> productosFicha;
        productosFicha = productoService.listarReportesProductoSucursal(idProducto, idSucursal);

        return ( productosFicha != null ) ? ResponseEntity.ok(productosFicha) : ResponseEntity.ok(Collections.emptyMap());
    }

    @PostMapping
    public ResponseEntity<String> guardarCategoria(@RequestBody Producto producto) {
        productoService.guardarNuevoProducto(producto);
        return ResponseEntity.ok("Categoria guardado correctamente");
    }

    @DeleteMapping("/{idProducto}")
    public ResponseEntity<String> eliminarProductoPorId(@PathVariable int idProducto) {
            productoService.eliminarProductoPorId(idProducto);
            return ResponseEntity.ok("Producto eliminado correctamente");
    }

    @PutMapping("/{idProducto}")
    public ResponseEntity<String> actualizarProducto(@PathVariable int idProducto, @RequestBody Producto producto) {
            productoService.actualizarProducto(idProducto, producto);
            return ResponseEntity.ok("Producto actualizado correctamente");
    }

}
