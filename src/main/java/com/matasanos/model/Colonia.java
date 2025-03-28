package com.matasanos.model;



public class Colonia {

    private int idColonia;// id_colonia
    private String nombreColonia;// nombre_colonia
    private Ciudad ciudad;

    public Colonia() {}
     
    public Colonia (int idColonia, String nombreColonia, Ciudad ciudad) {
        this.idColonia = idColonia;
        this.nombreColonia = nombreColonia;
        this.ciudad = ciudad;
    }

    public int getIdColonia() { return idColonia; }

    public void setIdColonia(int idColonia ){
        this.idColonia = idColonia;
    }

    public String getNombreColonia() { return nombreColonia; }

    public void setNombreColonia(String nombreColonia ){
        this.nombreColonia = nombreColonia;
    }

    public Ciudad getCiudad() { return ciudad; }

    public void setCiudad(Ciudad ciudad ){
        this.ciudad = ciudad;
    }
}