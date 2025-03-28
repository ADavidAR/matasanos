package com.matasanos.model;



public class Sucursal {

    private int idSucursal;// id_sucursal
    private Direccion direccion;
    private String nombreSucursal;// nombre_sucursal

    public Sucursal() {}
     
    public Sucursal (int idSucursal, Direccion direccion, String nombreSucursal) {
        this.idSucursal = idSucursal;
        this.direccion = direccion;
        this.nombreSucursal = nombreSucursal;
    }

    public int getIdSucursal() { return idSucursal; }

    public void setIdSucursal(int idSucursal ){
        this.idSucursal = idSucursal;
    }

    public Direccion getDireccion() { return direccion; }

    public void setDireccion(Direccion direccion ){
        this.direccion = direccion;
    }

    public String getNombreSucursal() { return nombreSucursal; }

    public void setNombreSucursal(String nombreSucursal ){
        this.nombreSucursal = nombreSucursal;
    }
}