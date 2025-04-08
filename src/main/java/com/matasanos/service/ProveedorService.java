package com.matasanos.service;

import com.matasanos.model.Proveedor;
import com.matasanos.repo.ProveedorRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorService {

    private final ProveedorRepo proveedorRepo;

    public ProveedorService(ProveedorRepo proveedorRepo) { this.proveedorRepo = proveedorRepo; }

    public List<Proveedor> listarProveedores() {
        return proveedorRepo.listarProveedores();
    }

    public Proveedor obtenerProveedorPorId(int idProveedor) {
        return proveedorRepo.obtenerProveedorPorId(idProveedor);
    }

    public void guardarNuevoProveedor(Proveedor proveedor) {
        proveedorRepo.guardarNuevoProveedor(proveedor);
    }

    public void eliminarProveedorPorId(int idProveedor) {
        proveedorRepo.eliminarProveedorPorId(idProveedor);
    }
}
