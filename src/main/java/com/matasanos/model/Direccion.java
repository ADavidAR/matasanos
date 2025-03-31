package com.matasanos.model;



public class Direccion {

    private Integer idDireccion;// id_direccion
    private String referencia;// referencia
    private Colonia colonia;

    public Direccion() {}
     
    public Direccion (Integer idDireccion, String referencia, Colonia colonia) {
        this.idDireccion = idDireccion;
        this.referencia = referencia;
        this.colonia = colonia;
    }

    public Integer getIdDireccion() { return idDireccion; }

    public void setIdDireccion(Integer idDireccion ){
        this.idDireccion = idDireccion;
    }

    public String getReferencia() { return referencia; }

    public void setReferencia(String referencia ){
        this.referencia = referencia;
    }

    public Colonia getColonia() { return colonia; }

    public void setColonia(Colonia colonia ){
        this.colonia = colonia;
    }
}