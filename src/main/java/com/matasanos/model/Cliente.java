package com.matasanos.model;

import java.time.LocalDate;

public class Cliente {

    private int idCliente;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String telefono;
    private String correo;
    private String rtn;
    private String cedula;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;
    private int idDireccion;
    private int idUsuarioCreacion;
    private int idUsuarioModificacion;

    public Cliente (int idCliente, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String telefono, String correo, String rtn, String cedula, LocalDate fechaCreacion, LocalDate fechaModificacion, int idDireccion, int idUsuarioCreacion, int idUsuarioModificacion) {
        this.idCliente = idCliente;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.telefono = telefono;
        this.correo = correo;
        this.rtn = rtn;
        this.cedula = cedula;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.idDireccion = idDireccion;
        this.idUsuarioCreacion = idUsuarioCreacion;
        this.idUsuarioModificacion = idUsuarioModificacion;
    }

    public int getIdcliente() { return idCliente; }

    public void setIdcliente(int idCliente ){
        this.idCliente = idCliente;
    }

    public String getPrimernombre() { return primerNombre; }

    public void setPrimernombre(String primerNombre ){
        this.primerNombre = primerNombre;
    }

    public String getSegundonombre() { return segundoNombre; }

    public void setSegundonombre(String segundoNombre ){
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerapellido() { return primerApellido; }

    public void setPrimerapellido(String primerApellido ){
        this.primerApellido = primerApellido;
    }

    public String getSegundoapellido() { return segundoApellido; }

    public void setSegundoapellido(String segundoApellido ){
        this.segundoApellido = segundoApellido;
    }

    public String getTelefono() { return telefono; }

    public void setTelefono(String telefono ){
        this.telefono = telefono;
    }

    public String getCorreo() { return correo; }

    public void setCorreo(String correo ){
        this.correo = correo;
    }

    public String getRtn() { return rtn; }

    public void setRtn(String rtn ){
        this.rtn = rtn;
    }

    public String getCedula() { return cedula; }

    public void setCedula(String cedula ){
        this.cedula = cedula;
    }

    public LocalDate getFechacreacion() { return fechaCreacion; }

    public void setFechacreacion(LocalDate fechaCreacion ){
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechamodificacion() { return fechaModificacion; }

    public void setFechamodificacion(LocalDate fechaModificacion ){
        this.fechaModificacion = fechaModificacion;
    }

    public int getIddireccion() { return idDireccion; }

    public void setIddireccion(int idDireccion ){
        this.idDireccion = idDireccion;
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