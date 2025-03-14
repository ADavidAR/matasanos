package com.matasanos.model;



public class Permiso {

    private int idPermiso;
    private String descripcion;

    public Permiso (int idPermiso, String descripcion) {
        this.idPermiso = idPermiso;
        this.descripcion = descripcion;
    }

    public int getIdpermiso() { return idPermiso; }

    public void setIdpermiso(int idPermiso ){
        this.idPermiso = idPermiso;
    }

    public String getDescripcion() { return descripcion; }

    public void setDescripcion(String descripcion ){
        this.descripcion = descripcion;
    }
}