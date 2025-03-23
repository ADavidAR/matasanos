package com.matasanos.model;

import java.time.LocalDate;

public class Usuario {

    private int idUsuario;// id_usuario
    private String usuario;// usuario
    private String contrasena;// contrasena
    private LocalDate fechaCreacion;// fecha_creacion
    private LocalDate fechaModificacion;// fecha_modificacion
    private Rol rol;
    private int idUsuarioCreacion;// id_usuario_creacion
    private int idUsuarioModificacion;// id_usuario_modificacion

    public Usuario (int idUsuario, String usuario, String contrasena, LocalDate fechaCreacion, LocalDate fechaModificacion, Rol rol, int idUsuarioCreacion, int idUsuarioModificacion) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.rol = rol;
        this.idUsuarioCreacion = idUsuarioCreacion;
        this.idUsuarioModificacion = idUsuarioModificacion;
    }

    public Usuario (int idUsuario, String usuario, String contrasena, Rol rol) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.fechaCreacion = null;
        this.fechaModificacion = null;
        this.rol = rol;
        this.idUsuarioCreacion = 0;
        this.idUsuarioModificacion = 0;
    }

    public Usuario (int idUsuario, String usuario, String contrasena) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.fechaCreacion = null;
        this.fechaModificacion = null;
        this.idUsuarioCreacion = 0;
        this.idUsuarioModificacion = 0;
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

    public Rol getRol() { return rol; }

    public void setRol(Rol rol ){
        this.rol = rol;
    }

    public int getIdusuariocreacion() { return idUsuarioCreacion; }

    public void setIdusuariocreacion(int idUsuarioCreacion ){
        this.idUsuarioCreacion = idUsuarioCreacion;
    }

    public int getIdusuariomodificacion() { return idUsuarioModificacion; }

    public void setIdusuariomodificacion(int idUsuarioModificacion ){
        this.idUsuarioModificacion = idUsuarioModificacion;
    }
}