package com.matasanos.model;



public class Funcion {

    private int idFuncion;// id_funcion
    private String descripcion;// descripcion

    public Funcion() {}
     
    public Funcion (int idFuncion, String descripcion) {
        this.idFuncion = idFuncion;
        this.descripcion = descripcion;
    }

    public int getIdFuncion() { return idFuncion; }

    public void setIdFuncion(int idFuncion ){
        this.idFuncion = idFuncion;
    }

    public String getDescripcion() { return descripcion; }

    public void setDescripcion(String descripcion ){
        this.descripcion = descripcion;
    }
}