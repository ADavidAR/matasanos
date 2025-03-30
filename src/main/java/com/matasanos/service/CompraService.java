package com.matasanos.service;

import com.matasanos.repo.CompraRepo;
import com.matasanos.model.*;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CompraService {
    private final CompraRepo compraRepo;


    public CompraService(CompraRepo compraRepo) {
        this.compraRepo = compraRepo;
    }

    public List<Proveedor> listaProveedor(){
        return compraRepo.listarProveedores();
    }
    public List<Producto> listaProductos(int idProveedor){
        return compraRepo.listarProductoProveedor(idProveedor);
    }
}
