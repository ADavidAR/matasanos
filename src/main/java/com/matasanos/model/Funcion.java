package com.matasanos.model;



public class Funcion {

    private int idFuncion;
    private String descripcion;

    public Funcion (int idFuncion, String descripcion) {
        this.idFuncion = idFuncion;
        this.descripcion = descripcion;
    }

    public int getIdfuncion() { return idFuncion; }

    public void setIdfuncion(int idFuncion ){
        this.idFuncion = idFuncion;
    }

    public String getDescripcion() { return descripcion; }

    public void setDescripcion(String descripcion ){
        this.descripcion = descripcion;
    }
}