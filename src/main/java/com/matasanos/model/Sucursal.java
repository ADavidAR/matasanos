package com.matasanos.model;



public class Sucursal {

    private int idSucursal;
    private String nombre;
    private String numEstablecimiento;

    public Sucursal (int idSucursal, String nombre, String numEstablecimiento) {
        this.idSucursal = idSucursal;
        this.nombre = nombre;
        this.numEstablecimiento = numEstablecimiento;
    }

    public int getIdsucursal() { return idSucursal; }

    public void setIdsucursal(int idSucursal ){
        this.idSucursal = idSucursal;
    }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre ){
        this.nombre = nombre;
    }

    public String getNumestablecimiento() { return numEstablecimiento; }

    public void setNumestablecimiento(String numEstablecimiento ){
        this.numEstablecimiento = numEstablecimiento;
    }
}