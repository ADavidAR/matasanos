package com.matasanos.model;



public class Permiso {

    private int idPermiso;// id_permiso
    private String descripcion;// descripcion
    private boolean accesoDirecto;// acceso_directo
    private String endpointUrl;// endpoint_url

    public Permiso() {}
     
    public Permiso (int idPermiso, String descripcion, boolean accesoDirecto, String endpointUrl) {
        this.idPermiso = idPermiso;
        this.descripcion = descripcion;
        this.accesoDirecto = accesoDirecto;
        this.endpointUrl = endpointUrl;
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

    public String getEndpointUrl() { return endpointUrl; }

    public void setEndpointUrl(String endpointUrl ){
        this.endpointUrl = endpointUrl;
    }
}