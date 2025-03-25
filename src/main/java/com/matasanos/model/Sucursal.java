package com.matasanos.model;



public class Sucursal {

    private int idSucursal;// id_sucursal
    private String nombreSucursal;// nombre_sucursal
    private String numEstablecimiento;// num_establecimiento

    public Sucursal() {}
     
    public Sucursal (int idSucursal, String nombreSucursal, String numEstablecimiento) {
        this.idSucursal = idSucursal;
        this.nombreSucursal = nombreSucursal;
        this.numEstablecimiento = numEstablecimiento;
    }

    public int getIdSucursal() { return idSucursal; }

    public void setIdSucursal(int idSucursal ){
        this.idSucursal = idSucursal;
    }

    public String getNombreSucursal() { return nombreSucursal; }

    public void setNombreSucursal(String nombreSucursal ){
        this.nombreSucursal = nombreSucursal;
    }

    public String getNumEstablecimiento() { return numEstablecimiento; }

    public void setNumEstablecimiento(String numEstablecimiento ){
        this.numEstablecimiento = numEstablecimiento;
    }
}