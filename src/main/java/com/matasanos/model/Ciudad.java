package com.matasanos.model;



public class Ciudad {

    private int idCiudad;
    private String ciudad;

    public Ciudad (int idCiudad, String ciudad) {
        this.idCiudad = idCiudad;
        this.ciudad = ciudad;
    }

    public int getIdciudad() { return idCiudad; }

    public void setIdciudad(int idCiudad ){
        this.idCiudad = idCiudad;
    }

    public String getCiudad() { return ciudad; }

    public void setCiudad(String ciudad ){
        this.ciudad = ciudad;
    }
}