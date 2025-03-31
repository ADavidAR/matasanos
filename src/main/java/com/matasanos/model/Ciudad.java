package com.matasanos.model;



public class Ciudad {

    private Integer idCiudad;// id_ciudad
    private String ciudad;// ciudad

    public Ciudad() {}
     
    public Ciudad (Integer idCiudad, String ciudad) {
        this.idCiudad = idCiudad;
        this.ciudad = ciudad;
    }

    public Integer getIdCiudad() { return idCiudad; }

    public void setIdCiudad(Integer idCiudad ){
        this.idCiudad = idCiudad;
    }

    public String getCiudad() { return ciudad; }

    public void setCiudad(String ciudad ){
        this.ciudad = ciudad;
    }
}