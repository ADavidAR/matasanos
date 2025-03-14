package com.matasanos.model;



public class Cargo {

    private int idCargo;
    private String descripcion;

    public Cargo (int idCargo, String descripcion) {
        this.idCargo = idCargo;
        this.descripcion = descripcion;
    }

    public int getIdcargo() { return idCargo; }

    public void setIdcargo(int idCargo ){
        this.idCargo = idCargo;
    }

    public String getDescripcion() { return descripcion; }

    public void setDescripcion(String descripcion ){
        this.descripcion = descripcion;
    }
}