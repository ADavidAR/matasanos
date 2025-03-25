package com.matasanos.model;



public class Caja {

    private int idCaja;// id_caja
    private int numCaja;// num_caja
    private Sucursal sucursal;

    public Caja() {}
     
    public Caja (int idCaja, int numCaja, Sucursal sucursal) {
        this.idCaja = idCaja;
        this.numCaja = numCaja;
        this.sucursal = sucursal;
    }

    public int getIdCaja() { return idCaja; }

    public void setIdCaja(int idCaja ){
        this.idCaja = idCaja;
    }

    public int getNumCaja() { return numCaja; }

    public void setNumCaja(int numCaja ){
        this.numCaja = numCaja;
    }

    public Sucursal getSucursal() { return sucursal; }

    public void setSucursal(Sucursal sucursal ){
        this.sucursal = sucursal;
    }
}