package com.matasanos.model;



public class Direcciones {

    private int idDireccion;// id_direccion
    private String colonia;// colonia
    private String direccion;// direccion
    private Ciudad ciudad;

    public Direcciones() {}
     
    public Direcciones (int idDireccion, String colonia, String direccion, Ciudad ciudad) {
        this.idDireccion = idDireccion;
        this.colonia = colonia;
        this.direccion = direccion;
        this.ciudad = ciudad;
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

    public Ciudad getCiudad() { return ciudad; }

    public void setCiudad(Ciudad ciudad ){
        this.ciudad = ciudad;
    }
}