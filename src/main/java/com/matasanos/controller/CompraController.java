package com.matasanos.controller;

import com.matasanos.model.Producto;
import com.matasanos.model.Proveedor;
import com.matasanos.service.CompraService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/compra")
public class CompraController {
    private final CompraService compraService;


    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @GetMapping("/cargar")
    @ResponseBody
    public List<Proveedor> listaProveedor(){
        return compraService.listaProveedor();
    }
     @PostMapping("/cargarProductos/{idProveedor}")
    @ResponseBody
    public List<Producto> listaProducto(@PathVariable int idProveedor){
         System.out.println(idProveedor);
        return compraService.listaProductos( idProveedor);

    }
}
