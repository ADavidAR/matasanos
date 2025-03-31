package com.matasanos.model;



public class Cargo {

    private Integer idCargo;// id_cargo
    private String descripcion;// descripcion

    public Cargo() {}
     
    public Cargo (Integer idCargo, String descripcion) {
        this.idCargo = idCargo;
        this.descripcion = descripcion;
    }

    public Integer getIdCargo() { return idCargo; }

    public void setIdCargo(Integer idCargo ){
        this.idCargo = idCargo;
    }

    public String getDescripcion() { return descripcion; }

    public void setDescripcion(String descripcion ){
        this.descripcion = descripcion;
    }
}