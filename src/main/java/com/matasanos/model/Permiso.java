package com.matasanos.model;



public class Permiso {

    private Integer idPermiso;// id_permiso
    private String descripcion;// descripcion
    private Boolean accesoDirecto;// acceso_directo
    private String endpointUrl;// endpoint_url

    public Permiso() {}
     
    public Permiso (Integer idPermiso, String descripcion, Boolean accesoDirecto, String endpointUrl) {
        this.idPermiso = idPermiso;
        this.descripcion = descripcion;
        this.accesoDirecto = accesoDirecto;
        this.endpointUrl = endpointUrl;
    }

    public Integer getIdPermiso() { return idPermiso; }

    public void setIdPermiso(Integer idPermiso ){
        this.idPermiso = idPermiso;
    }

    public String getDescripcion() { return descripcion; }

    public void setDescripcion(String descripcion ){
        this.descripcion = descripcion;
    }

    public Boolean getAccesoDirecto() { return accesoDirecto; }

    public void setAccesoDirecto(Boolean accesoDirecto ){
        this.accesoDirecto = accesoDirecto;
    }

    public String getEndpointUrl() { return endpointUrl; }

    public void setEndpointUrl(String endpointUrl ){
        this.endpointUrl = endpointUrl;
    }
}