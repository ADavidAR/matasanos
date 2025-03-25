package com.matasanos.model;



public class Cargo {

    private int idCargo;// id_cargo
    private String descripcion;// descripcion

    public Cargo() {}
     
    public Cargo (int idCargo, String descripcion) {
        this.idCargo = idCargo;
        this.descripcion = descripcion;
    }

    public int getIdCargo() { return idCargo; }

    public void setIdCargo(int idCargo ){
        this.idCargo = idCargo;
    }

    public String getDescripcion() { return descripcion; }

    public void setDescripcion(String descripcion ){
        this.descripcion = descripcion;
    }
}