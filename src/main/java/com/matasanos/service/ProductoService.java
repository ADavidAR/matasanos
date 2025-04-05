package com.matasanos.service;

import com.matasanos.model.Producto;
import com.matasanos.repo.ProductoRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepo productoRepo;

    public ProductoService(ProductoRepo productoRepo) {
        this.productoRepo = productoRepo;
    }

    public Producto obtenerProductoDeSucursal(int idProducto, int idSucursal) {
        return productoRepo.obtenerProductoDeSucursal(idProducto, idSucursal);
    }

    public Producto obtenerProducto(int idProducto) {
        return productoRepo.obtenerProducto(idProducto);
    }

    public List<Producto> listarProductosDeSucursal(int idSucursal) {
        return productoRepo.listarProductosDeSucursal(idSucursal);
    }

    public List<Producto> listarProductos() {
        return productoRepo.listarProductos();
    }

    public List<Producto> filtrarProductosDeSucursalPorNombre(String filtro, int idSucursal) {
        return productoRepo.filtrarProductosDeSucursalPorNombre(filtro, idSucursal);
    }

    public List<Producto> filtrarProductosPorNombre(String filtro) {
        return productoRepo.filtrarProductosPorNombre(filtro);
    }

    public List<Producto> listarProductosDeSucursalPorCategoria(int idSucursal, int idCategoria) {
        return productoRepo.listarProductosDeSucursalPorCategoria(idSucursal, idCategoria);
    }

    public List<Producto> filtrarProductosDeSucursalPorNombreSimplificado(int idSucursal, String filtro) {
        return productoRepo.filtrarProductosDeSucursalPorNombreSimplificado(idSucursal, filtro);
    }
}