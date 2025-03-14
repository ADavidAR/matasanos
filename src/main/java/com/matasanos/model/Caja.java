package com.matasanos.model;



public class Caja {

    private int idCaja;
    private int numCaja;
    private int idSucursal;

    public Caja (int idCaja, int numCaja, int idSucursal) {
        this.idCaja = idCaja;
        this.numCaja = numCaja;
        this.idSucursal = idSucursal;
    }

    public int getIdcaja() { return idCaja; }

    public void setIdcaja(int idCaja ){
        this.idCaja = idCaja;
    }

    public int getNumcaja() { return numCaja; }

    public void setNumcaja(int numCaja ){
        this.numCaja = numCaja;
    }

    public int getIdsucursal() { return idSucursal; }

    public void setIdsucursal(int idSucursal ){
        this.idSucursal = idSucursal;
    }
}