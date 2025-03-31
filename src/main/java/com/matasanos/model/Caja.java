package com.matasanos.model;



public class Caja {

    private Integer idCaja;// id_caja
    private Integer numCaja;// num_caja
    private Sucursal sucursal;

    public Caja() {}
     
    public Caja (Integer idCaja, Integer numCaja, Sucursal sucursal) {
        this.idCaja = idCaja;
        this.numCaja = numCaja;
        this.sucursal = sucursal;
    }

    public Integer getIdCaja() { return idCaja; }

    public void setIdCaja(Integer idCaja ){
        this.idCaja = idCaja;
    }

    public Integer getNumCaja() { return numCaja; }

    public void setNumCaja(Integer numCaja ){
        this.numCaja = numCaja;
    }

    public Sucursal getSucursal() { return sucursal; }

    public void setSucursal(Sucursal sucursal ){
        this.sucursal = sucursal;
    }
}