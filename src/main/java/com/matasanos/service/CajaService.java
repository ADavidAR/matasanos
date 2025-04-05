package com.matasanos.service;

import com.matasanos.model.Caja;
import com.matasanos.repo.CajaRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CajaService {
    private final CajaRepo cajaRepo;

    public CajaService(CajaRepo cajaRepo) {
        this.cajaRepo = cajaRepo;
    }
    public List<Caja> listarCajas() {
        return cajaRepo.listarCajas();
    }

    public List<Caja> listarCajasPorSucursal(int idSucursal) {
        return cajaRepo.listarCajasPorSucursal(idSucursal);
    }

    public boolean verificarNumCaja(int numCaja, int idSucursal) {
        Caja c = cajaRepo.obtenerCaja(numCaja, idSucursal);
        return c != null;
    }


    public boolean enUso(int idSucursal) {
        return cajaRepo.enUso(idSucursal);
    }

    public boolean existe(int idCaja) {
        return cajaRepo.existe(idCaja);
    }

    public void eliminarSucursal(int idSucursal) {
        cajaRepo.eliminar(idSucursal);
    }

    public void modificar(Caja caja) {
            cajaRepo.modificar(caja);
    }

    public void agregar(Caja caja) {
        cajaRepo.agregar(caja);
    }
}
