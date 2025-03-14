package com.matasanos.model;



public class RolPermisos {

    private int idRolPermiso;
    private int idRol;
    private int idPermiso;

    public RolPermisos (int idRolPermiso, int idRol, int idPermiso) {
        this.idRolPermiso = idRolPermiso;
        this.idRol = idRol;
        this.idPermiso = idPermiso;
    }

    public int getIdrolpermiso() { return idRolPermiso; }

    public void setIdrolpermiso(int idRolPermiso ){
        this.idRolPermiso = idRolPermiso;
    }

    public int getIdrol() { return idRol; }

    public void setIdrol(int idRol ){
        this.idRol = idRol;
    }

    public int getIdpermiso() { return idPermiso; }

    public void setIdpermiso(int idPermiso ){
        this.idPermiso = idPermiso;
    }
}