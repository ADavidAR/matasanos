package com.matasanos.model;



public class Rol {

    private int idRol;
    private String nombreRol;

    public Rol (int idRol, String nombreRol) {
        this.idRol = idRol;
        this.nombreRol = nombreRol;
    }

    public int getIdrol() { return idRol; }

    public void setIdrol(int idRol ){
        this.idRol = idRol;
    }

    public String getNombrerol() { return nombreRol; }

    public void setNombrerol(String nombreRol ){
        this.nombreRol = nombreRol;
    }
}