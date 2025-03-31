package com.matasanos.model;



public class RolPermiso {

    private Integer idRolPermiso;// id_rol_permiso
    private Rol rol;
    private Permiso permiso;
    private Boolean acceso;// acceso
    private Boolean modificacion;// modificacion
    private Boolean eliminacion;// eliminacion
    private Boolean creacion;// creacion

    public RolPermiso() {}
     
    public RolPermiso (Integer idRolPermiso, Rol rol, Permiso permiso, Boolean acceso, Boolean modificacion, Boolean eliminacion, Boolean creacion) {
        this.idRolPermiso = idRolPermiso;
        this.rol = rol;
        this.permiso = permiso;
        this.acceso = acceso;
        this.modificacion = modificacion;
        this.eliminacion = eliminacion;
        this.creacion = creacion;
    }

    public Integer getIdRolPermiso() { return idRolPermiso; }

    public void setIdRolPermiso(Integer idRolPermiso ){
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

    public Boolean getAcceso() { return acceso; }

    public void setAcceso(Boolean acceso ){
        this.acceso = acceso;
    }

    public Boolean getModificacion() { return modificacion; }

    public void setModificacion(Boolean modificacion ){
        this.modificacion = modificacion;
    }

    public Boolean getEliminacion() { return eliminacion; }

    public void setEliminacion(Boolean eliminacion ){
        this.eliminacion = eliminacion;
    }

    public Boolean getCreacion() { return creacion; }

    public void setCreacion(Boolean creacion ){
        this.creacion = creacion;
    }
}