package com.matasanos.model;



public class Ciudad {

    private int idCiudad;// id_ciudad
    private String ciudad;// ciudad

    public Ciudad() {}
     
    public Ciudad (int idCiudad, String ciudad) {
        this.idCiudad = idCiudad;
        this.ciudad = ciudad;
    }

    public int getIdCiudad() { return idCiudad; }

    public void setIdCiudad(int idCiudad ){
        this.idCiudad = idCiudad;
    }

    public String getCiudad() { return ciudad; }

    public void setCiudad(String ciudad ){
        this.ciudad = ciudad;
    }
}