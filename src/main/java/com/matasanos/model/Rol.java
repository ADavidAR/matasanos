package com.matasanos.model;


import java.util.List;

public class Rol {

    private Integer idRol;// id_rol
    private String nombreRol;// nombre_rol
    private List<RolPermiso> permisos;
    public Rol() {}
     
    public Rol (Integer idRol, String nombreRol) {
        this.idRol = idRol;
        this.nombreRol = nombreRol;
    }

    public Integer getIdRol() { return idRol; }

    public void setIdRol(Integer idRol ){
        this.idRol = idRol;
    }

    public String getNombreRol() { return nombreRol; }

    public void setNombreRol(String nombreRol ){
        this.nombreRol = nombreRol;
    }

    public List<RolPermiso> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<RolPermiso> permisos) {
        this.permisos = permisos;
    }
}