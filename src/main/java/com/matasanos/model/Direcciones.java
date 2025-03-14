package com.matasanos.model;



public class Direcciones {

    private int idDireccion;
    private String colonia;
    private String direccion;
    private int idCiudad;

    public Direcciones (int idDireccion, String colonia, String direccion, int idCiudad) {
        this.idDireccion = idDireccion;
        this.colonia = colonia;
        this.direccion = direccion;
        this.idCiudad = idCiudad;
    }

    public int getIddireccion() { return idDireccion; }

    public void setIddireccion(int idDireccion ){
        this.idDireccion = idDireccion;
    }

    public String getColonia() { return colonia; }

    public void setColonia(String colonia ){
        this.colonia = colonia;
    }

    public String getDireccion() { return direccion; }

    public void setDireccion(String direccion ){
        this.direccion = direccion;
    }

    public int getIdciudad() { return idCiudad; }

    public void setIdciudad(int idCiudad ){
        this.idCiudad = idCiudad;
    }
}