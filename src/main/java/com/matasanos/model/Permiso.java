package com.matasanos.model;



public class Permiso {

    private int idPermiso;// id_permiso
    private String descripcion;// descripcion
    private boolean accesoDirecto;// acceso_directo
    private String endpoint;// endpoint

    public Permiso() {}
     
    public Permiso (int idPermiso, String descripcion, boolean accesoDirecto, String endpoint) {
        this.idPermiso = idPermiso;
        this.descripcion = descripcion;
        this.accesoDirecto = accesoDirecto;
        this.endpoint = endpoint;
    }

    public int getIdPermiso() { return idPermiso; }

    public void setIdPermiso(int idPermiso ){
        this.idPermiso = idPermiso;
    }

    public String getDescripcion() { return descripcion; }

    public void setDescripcion(String descripcion ){
        this.descripcion = descripcion;
    }

    public boolean getAccesoDirecto() { return accesoDirecto; }

    public void setAccesoDirecto(boolean accesoDirecto ){
        this.accesoDirecto = accesoDirecto;
    }

    public String getEndpoint() { return endpoint; }

    public void setEndpoint(String endpoint ){
        this.endpoint = endpoint;
    }
}