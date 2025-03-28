package com.matasanos.model;



public class MetodoPago {

    private int idMetodo;// id_metodo
    private String descripcion;// descripcion

    public MetodoPago() {}
     
    public MetodoPago (int idMetodo, String descripcion) {
        this.idMetodo = idMetodo;
        this.descripcion = descripcion;
    }

    public int getIdMetodo() { return idMetodo; }

    public void setIdMetodo(int idMetodo ){
        this.idMetodo = idMetodo;
    }

    public String getDescripcion() { return descripcion; }

    public void setDescripcion(String descripcion ){
        this.descripcion = descripcion;
    }
}