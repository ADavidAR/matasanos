package com.matasanos.model;



public class Funcion {

    private Integer idFuncion;// id_funcion
    private String descripcion;// descripcion

    public Funcion() {}
     
    public Funcion (Integer idFuncion, String descripcion) {
        this.idFuncion = idFuncion;
        this.descripcion = descripcion;
    }

    public Integer getIdFuncion() { return idFuncion; }

    public void setIdFuncion(Integer idFuncion ){
        this.idFuncion = idFuncion;
    }

    public String getDescripcion() { return descripcion; }

    public void setDescripcion(String descripcion ){
        this.descripcion = descripcion;
    }
}