package com.matasanos.controller;

import com.matasanos.model.Compra;
import com.matasanos.model.Producto;
import com.matasanos.model.ProductoCompra;
import com.matasanos.model.Proveedor;
import com.matasanos.service.CompraService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comprar")
public class CompraController {
    private final CompraService compraService;


    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @PostMapping("/crearCompra/{idProveedor}")
    public  void crearSolicitudCompra(@PathVariable int idProveedor,@RequestBody List<Map<String,Object>> datos){
       compraService.crearCompra(idProveedor,datos);
    }

    @GetMapping("/proveedores")
    @ResponseBody
    public List<Proveedor> listaProveedor(){
        return compraService.listaProveedor();
    }

    @PostMapping("/cargarProductos/{idProveedor}")
    @ResponseBody
    public List<Producto> listaProducto(@PathVariable int idProveedor){

        return compraService.listaProductos( idProveedor);

    }
    @GetMapping("/cargarCompras")
    @ResponseBody
    public  List<Compra> comprasPendientes(){
        return  compraService.listarCompras();
    }

    @GetMapping("/productosCompra/{idCompra}")
    @ResponseBody
    public  List<ProductoCompra> lisarProductosCompra( @PathVariable int idCompra){
        return compraService.lisarProductosCompra(idCompra);
    }
    @GetMapping("/datosCompra/{idCompra}")
    @ResponseBody
    public  Compra compra( @PathVariable int idCompra){
        return compraService.compra(idCompra);
    }

}
