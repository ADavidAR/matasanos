package com.matasanos.model;



public class MetodoPago {

    private Integer idMetodo;// id_metodo
    private String descripcion;// descripcion

    public MetodoPago() {}
     
    public MetodoPago (Integer idMetodo, String descripcion) {
        this.idMetodo = idMetodo;
        this.descripcion = descripcion;
    }

    public Integer getIdMetodo() { return idMetodo; }

    public void setIdMetodo(Integer idMetodo ){
        this.idMetodo = idMetodo;
    }

    public String getDescripcion() { return descripcion; }

    public void setDescripcion(String descripcion ){
        this.descripcion = descripcion;
    }
}