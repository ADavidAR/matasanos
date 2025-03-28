package com.matasanos.model;



public class Direccion {

    private int idDireccion;// id_direccion
    private String referencia;// referencia
    private Colonia colonia;

    public Direccion() {}
     
    public Direccion (int idDireccion, String referencia, Colonia colonia) {
        this.idDireccion = idDireccion;
        this.referencia = referencia;
        this.colonia = colonia;
    }

    public int getIdDireccion() { return idDireccion; }

    public void setIdDireccion(int idDireccion ){
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