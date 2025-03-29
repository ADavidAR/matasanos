package com.matasanos.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Empleado {

    private int idEmpleado;// id_empleado
    private BigDecimal salario;// salario
    private LocalDate fechaContratacion;// fecha_contratacion
    private LocalDate fechaBaja;// fecha_baja
    private boolean activo;// activo
    private LocalDate fechaCreacion;// fecha_creacion
    private LocalDate fechaModificacion;// fecha_modificacion
    private Persona persona;
    private Cargo cargo;
    private Sucursal sucursal;
    private int idUsuarioCreacion;// id_usuario_creacion
    private int idUsuarioModificacion;// id_usuario_modificacion

    public Empleado() {}
     
    public Empleado (int idEmpleado, BigDecimal salario, LocalDate fechaContratacion, LocalDate fechaBaja, boolean activo, LocalDate fechaCreacion, LocalDate fechaModificacion, Persona persona, Cargo cargo, Sucursal sucursal, int idUsuarioCreacion, int idUsuarioModificacion) {
        this.idEmpleado = idEmpleado;
        this.salario = salario;
        this.fechaContratacion = fechaContratacion;
        this.fechaBaja = fechaBaja;
        this.activo = activo;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.persona = persona;
        this.cargo = cargo;
        this.sucursal = sucursal;
        this.idUsuarioCreacion = idUsuarioCreacion;
        this.idUsuarioModificacion = idUsuarioModificacion;
    }

    public int getIdEmpleado() { return idEmpleado; }

    public void setIdEmpleado(int idEmpleado ){
        this.idEmpleado = idEmpleado;
    }

    public BigDecimal getSalario() { return salario; }

    public void setSalario(BigDecimal salario ){
        this.salario = salario;
    }

    public LocalDate getFechaContratacion() { return fechaContratacion; }

    public void setFechaContratacion(LocalDate fechaContratacion ){
        this.fechaContratacion = fechaContratacion;
    }

    public LocalDate getFechaBaja() { return fechaBaja; }

    public void setFechaBaja(LocalDate fechaBaja ){
        this.fechaBaja = fechaBaja;
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

    public Persona getPersona() { return persona; }

    public void setPersona(Persona persona ){
        this.persona = persona;
    }

    public Cargo getCargo() { return cargo; }

    public void setCargo(Cargo cargo ){
        this.cargo = cargo;
    }

    public Sucursal getSucursal() { return sucursal; }

    public void setSucursal(Sucursal sucursal ){
        this.sucursal = sucursal;
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