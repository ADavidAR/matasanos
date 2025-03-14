package com.matasanos.model;

import java.time.LocalDate;
import java.util.List;

public class Usuario {

    private int idUsuario;
    private String usuario;
    private String contrasena;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;
    private int idRol;
    private String nombreRol;
    private int idUsuarioCreacion;
    private int idUsuarioModificacion;

    private List<Permiso> permisos;

    public Usuario (int idUsuario, String usuario, String contrasena, LocalDate fechaCreacion, LocalDate fechaModificacion, int idRol, String nombreRol, int idUsuarioCreacion, int idUsuarioModificacion) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.idRol = idRol;
        this.nombreRol = nombreRol;
        this.idUsuarioCreacion = idUsuarioCreacion;
        this.idUsuarioModificacion = idUsuarioModificacion;
    }

    public int getIdusuario() { return idUsuario; }

    public void setIdusuario(int idUsuario ){
        this.idUsuario = idUsuario;
    }

    public String getUsuario() { return usuario; }

    public void setUsuario(String usuario ){
        this.usuario = usuario;
    }

    public String getContrasena() { return contrasena; }

    public void setContrasena(String contrasena ){
        this.contrasena = contrasena;
    }

    public LocalDate getFechacreacion() { return fechaCreacion; }

    public void setFechacreacion(LocalDate fechaCreacion ){
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechamodificacion() { return fechaModificacion; }

    public void setFechamodificacion(LocalDate fechaModificacion ){
        this.fechaModificacion = fechaModificacion;
    }

    public int getIdrol() { return idRol; }

    public void setIdrol(int idRol ){
        this.idRol = idRol;
    }

    public int getIdusuariocreacion() { return idUsuarioCreacion; }

    public void setIdusuariocreacion(int idUsuarioCreacion ){
        this.idUsuarioCreacion = idUsuarioCreacion;
    }

    public int getIdusuariomodificacion() { return idUsuarioModificacion; }

    public void setIdusuariomodificacion(int idUsuarioModificacion ){
        this.idUsuarioModificacion = idUsuarioModificacion;
    }

    public List<Permiso> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<Permiso> permisos) {
        this.permisos = permisos;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
}