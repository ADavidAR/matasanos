package com.matasanos.model;

import java.time.LocalDate;

public class Usuario {

    private Integer idUsuario;// id_usuario
    private String usuario;// usuario
    private String contrasena;// contrasena
    private Boolean activo;// activo
    private LocalDate fechaCreacion;// fecha_creacion
    private LocalDate fechaModificacion;// fecha_modificacion
    private Rol rol;
    private Empleado empleado;
    private Integer idUsuarioCreacion;// id_usuario_creacion
    private Integer idUsuarioModificacion;// id_usuario_modificacion

    public Usuario() {}
     
    public Usuario (Integer idUsuario, String usuario, String contrasena, Boolean activo, LocalDate fechaCreacion, LocalDate fechaModificacion, Rol rol, Empleado empleado, Integer idUsuarioCreacion, Integer idUsuarioModificacion) {
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

    public Integer getIdUsuario() { return idUsuario; }

    public void setIdUsuario(Integer idUsuario ){
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

    public Boolean getActivo() { return activo; }

    public void setActivo(Boolean activo ){
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

    public Empleado getIdEmpleado() { return empleado; }

    public void setIdEmpleado(Empleado empleado){
        this.empleado = empleado;
    }

    public Integer getIdUsuarioCreacion() { return idUsuarioCreacion; }

    public void setIdUsuarioCreacion(Integer idUsuarioCreacion ){
        this.idUsuarioCreacion = idUsuarioCreacion;
    }

    public Integer getIdUsuarioModificacion() { return idUsuarioModificacion; }

    public void setIdUsuarioModificacion(Integer idUsuarioModificacion ){
        this.idUsuarioModificacion = idUsuarioModificacion;
    }
}