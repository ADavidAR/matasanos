package com.matasanos.model;

import java.time.LocalDate;

public class Usuario {

    private int idUsuario;// id_usuario
    private String usuario;// usuario
    private String contrasena;// contrasena
    private boolean activo;// activo
    private LocalDate fechaCreacion;// fecha_creacion
    private LocalDate fechaModificacion;// fecha_modificacion
    private Rol rol;
    private Empleado empleado;
    private int idUsuarioCreacion;// id_usuario_creacion
    private int idUsuarioModificacion;// id_usuario_modificacion

    public Usuario() {}
     
    public Usuario (int idUsuario, String usuario, String contrasena, boolean activo, LocalDate fechaCreacion, LocalDate fechaModificacion, Rol rol, Empleado empleado, int idUsuarioCreacion, int idUsuarioModificacion) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.activo = activo;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.rol = rol;
        this.empleado = empleado;
        this.idUsuarioCreacion = idUsuarioCreacion;
        this.idUsuarioModificacion = idUsuarioModificacion;
    }

    public Usuario(int idUsuario, String usuario, String contrasena, Rol rol) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.rol = rol;
        this.fechaCreacion = null;
        this.fechaModificacion = null;
        this.idUsuarioCreacion = 0;
        this.idUsuarioModificacion = 0;
    }

    public Usuario(int idUsuario, String usuario, String contrasena) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.rol = null;
        this.fechaCreacion = null;
        this.fechaModificacion = null;
        this.idUsuarioCreacion = 0;
        this.idUsuarioModificacion = 0;
    }

    public int getIdUsuario() { return idUsuario; }

    public void setIdUsuario(int idUsuario ){
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

    public boolean getActivo() { return activo; }

    public void setActivo(boolean activo ){
        this.activo = activo;
    }

    public LocalDate getFechaCreacion() { return fechaCreacion; }

    public void setFechaCreacion(LocalDate fechaCreacion ){
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaModificacion() { return fechaModificacion; }

    public void setFechaModificacion(LocalDate fechaModificacion ){
        this.fechaModificacion = fechaModificacion;
    }

    public Rol getRol() { return rol; }

    public void setRol(Rol rol ){
        this.rol = rol;
    }

    public Empleado getEmpleado() { return empleado; }

    public void setEmpleado(Empleado empleado ){
        this.empleado = empleado;
    }

    public int getIdUsuarioCreacion() { return idUsuarioCreacion; }

    public void setIdUsuarioCreacion(int idUsuarioCreacion ){
        this.idUsuarioCreacion = idUsuarioCreacion;
    }

    public int getIdUsuarioModificacion() { return idUsuarioModificacion; }

    public void setIdUsuarioModificacion(int idUsuarioModificacion ){
        this.idUsuarioModificacion = idUsuarioModificacion;
    }
}