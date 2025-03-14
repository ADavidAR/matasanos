package com.matasanos.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Empleado {

    private int idEmpleado;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String telefono;
    private String correo;
    private BigDecimal salario;
    private LocalDate fechaContratacion;
    private LocalDate fechaBaja;
    private boolean activo;
    private LocalDate fechaModificacion;
    private int idDireccion;
    private int idCargo;
    private int idUsuario;
    private int idSucursal;
    private int idUsuarioCreacion;
    private int idUsuarioModificacion;

    public Empleado (int idEmpleado, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String telefono, String correo, BigDecimal salario, LocalDate fechaContratacion, LocalDate fechaBaja, boolean activo, LocalDate fechaModificacion, int idDireccion, int idCargo, int idUsuario, int idSucursal, int idUsuarioCreacion, int idUsuarioModificacion) {
        this.idEmpleado = idEmpleado;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.telefono = telefono;
        this.correo = correo;
        this.salario = salario;
        this.fechaContratacion = fechaContratacion;
        this.fechaBaja = fechaBaja;
        this.activo = activo;
        this.fechaModificacion = fechaModificacion;
        this.idDireccion = idDireccion;
        this.idCargo = idCargo;
        this.idUsuario = idUsuario;
        this.idSucursal = idSucursal;
        this.idUsuarioCreacion = idUsuarioCreacion;
        this.idUsuarioModificacion = idUsuarioModificacion;
    }

    public int getIdempleado() { return idEmpleado; }

    public void setIdempleado(int idEmpleado ){
        this.idEmpleado = idEmpleado;
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

    public BigDecimal getSalario() { return salario; }

    public void setSalario(BigDecimal salario ){
        this.salario = salario;
    }

    public LocalDate getFechacontratacion() { return fechaContratacion; }

    public void setFechacontratacion(LocalDate fechaContratacion ){
        this.fechaContratacion = fechaContratacion;
    }

    public LocalDate getFechabaja() { return fechaBaja; }

    public void setFechabaja(LocalDate fechaBaja ){
        this.fechaBaja = fechaBaja;
    }

    public boolean getActivo() { return activo; }

    public void setActivo(boolean activo ){
        this.activo = activo;
    }

    public LocalDate getFechamodificacion() { return fechaModificacion; }

    public void setFechamodificacion(LocalDate fechaModificacion ){
        this.fechaModificacion = fechaModificacion;
    }

    public int getIddireccion() { return idDireccion; }

    public void setIddireccion(int idDireccion ){
        this.idDireccion = idDireccion;
    }

    public int getIdcargo() { return idCargo; }

    public void setIdcargo(int idCargo ){
        this.idCargo = idCargo;
    }

    public int getIdusuario() { return idUsuario; }

    public void setIdusuario(int idUsuario ){
        this.idUsuario = idUsuario;
    }

    public int getIdsucursal() { return idSucursal; }

    public void setIdsucursal(int idSucursal ){
        this.idSucursal = idSucursal;
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