package com.matasanos.model;



public class RolPermisos {

    private int idRolPermiso;// id_rol_permiso
    private Rol rol;
    private Permiso permiso;
    private boolean modificacion;// modificacion
    private boolean eliminacion;// eliminacion
    private boolean creacion;// creacion

    public RolPermisos (int idRolPermiso, Rol rol, Permiso permiso, boolean modificacion, boolean eliminacion, boolean creacion) {
        this.idRolPermiso = idRolPermiso;
        this.rol = rol;
        this.permiso = permiso;
        this.modificacion = modificacion;
        this.eliminacion = eliminacion;
        this.creacion = creacion;
    }

    public int getIdRolPermiso() { return idRolPermiso; }

    public void setIdRolPermiso(int idRolPermiso ){
        this.idRolPermiso = idRolPermiso;
    }

    public Rol getRol() { return rol; }

    public void setRol(Rol rol ){
        this.rol = rol;
    }

    public Permiso getPermiso() { return permiso; }

    public void setPermiso(Permiso permiso ){
        this.permiso = permiso;
    }

    public boolean getModificacion() { return modificacion; }

    public void setModificacion(boolean modificacion ){
        this.modificacion = modificacion;
    }

    public boolean getEliminacion() { return eliminacion; }

    public void setEliminacion(boolean eliminacion ){
        this.eliminacion = eliminacion;
    }

    public boolean getCreacion() { return creacion; }

    public void setCreacion(boolean creacion ){
        this.creacion = creacion;
    }
}