package com.matasanos.model;

import java.time.LocalDate;
import java.util.List;

public class Rol {

    private int idRol;// id_rol
    private String nombreRol;// nombre_rol
    private LocalDate fechaCreacion;// fecha_creacion
    private LocalDate fechaModificacion;// fecha_modificacion
    private int idUsuarioCreacion;// id_usuario_creacion
    private int idUsuarioModificacion;// id_usuario_modificacion

    List<RolPermisos> permisos;
    public Rol (int idRol, String nombreRol, LocalDate fechaCreacion, LocalDate fechaModificacion, int idUsuarioCreacion, int idUsuarioModificacion) {
        this.idRol = idRol;
        this.nombreRol = nombreRol;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.idUsuarioCreacion = idUsuarioCreacion;
        this.idUsuarioModificacion = idUsuarioModificacion;
    }

    public Rol (int idRol, String nombreRol) {
        this.idRol = idRol;
        this.nombreRol = nombreRol;
        this.fechaCreacion = null;
        this.fechaModificacion = null;
        this.idUsuarioCreacion = 0;
        this.idUsuarioModificacion = 0;
    }

    public int getIdrol() { return idRol; }

    public void setIdrol(int idRol ){
        this.idRol = idRol;
    }

    public String getNombrerol() { return nombreRol; }

    public void setNombrerol(String nombreRol ){
        this.nombreRol = nombreRol;
    }

    public LocalDate getFechacreacion() { return fechaCreacion; }

    public void setFechacreacion(LocalDate fechaCreacion ){
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechamodificacion() { return fechaModificacion; }

    public void setFechamodificacion(LocalDate fechaModificacion ){
        this.fechaModificacion = fechaModificacion;
    }

    public int getIdusuariocreacion() { return idUsuarioCreacion; }

    public void setIdusuariocreacion(int idUsuarioCreacion ){
        this.idUsuarioCreacion = idUsuarioCreacion;
    }

    public int getIdusuariomodificacion() { return idUsuarioModificacion; }

    public void setIdusuariomodificacion(int idUsuarioModificacion ){
        this.idUsuarioModificacion = idUsuarioModificacion;
    }

    public List<RolPermisos> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<RolPermisos> permisos) {
        this.permisos = permisos;
    }
}