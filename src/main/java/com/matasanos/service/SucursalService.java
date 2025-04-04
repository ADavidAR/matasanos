package com.matasanos.service;

import com.matasanos.model.Sucursal;
import com.matasanos.repo.SucursalRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalService {
    private final SucursalRepo sucursalRepo;

    public SucursalService(SucursalRepo sucursalRepo) {
        this.sucursalRepo = sucursalRepo;
    }
    public List<Sucursal> listarSucursales() {
        return sucursalRepo.listarSucursalesSimples();
    }

    public boolean verificarModificacionNombre(String nombreSucursal, int idSucursal) {
        Sucursal u = sucursalRepo.obtenerSucursal(nombreSucursal);
        if(u != null && u.getIdSucursal() != idSucursal) return false;

        return false;
    }

    public boolean verificarCreacionNombre(String nombreSucursal) {
        Sucursal u = sucursalRepo.obtenerSucursal(nombreSucursal);
        if(u != null) return false;

        return false;
    }

    public Sucursal obtenerSucursalDireccion(int idSucursal) {
        return sucursalRepo.obtenerSucursalDireccion(idSucursal);
    }

    public boolean enUso(int idSucursal) {
        return sucursalRepo.enUso(idSucursal);
    }

    public boolean existe(int idSucursal) {
        return sucursalRepo.existe(idSucursal);
    }

    public void eliminarSucursal(int idSucursal) {
        sucursalRepo.eliminar(idSucursal);
    }

    public void modificarSucursal(Sucursal sucursal) {
            sucursalRepo.modificar(sucursal);
    }

    public void agregarSucursal(Sucursal sucursal) {
        sucursalRepo.agregar(sucursal);
    }
}
