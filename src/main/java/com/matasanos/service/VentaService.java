package com.matasanos.service;

import java.util.List;

import com.matasanos.dto.ProductoCantidadDto;
import com.matasanos.model.Categoria;
import com.matasanos.model.Departamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matasanos.model.Producto;
import com.matasanos.repo.VentaRepository;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    public VentaService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    public List<Producto> listarProductosDeSucursal(int id){
        return this.ventaRepository.listarProductosDeSucursal(id);
    }

    public List<Departamento> listarDepartamentos(){
       return this.ventaRepository.listarDepartamentos();
    }

    public List<Categoria> listarCategorias(){
        return this.ventaRepository.listarCategorias();
    }

    public void procesarOrden(List<ProductoCantidadDto> carrito){
        for(ProductoCantidadDto p : carrito){
            this.ventaRepository.procesarOrden(p.getCantidad(), p.getIdProducto(), p.getIdSucursal());
        }
    }
}
