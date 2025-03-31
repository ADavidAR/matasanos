package com.matasanos.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Empleado {

    private Integer idEmpleado;// id_empleado
    private BigDecimal salario;// salario
    private LocalDate fechaContratacion;// fecha_contratacion
    private LocalDate fechaBaja;// fecha_baja
    private Boolean activo;// activo
    private LocalDate fechaCreacion;// fecha_creacion
    private LocalDate fechaModificacion;// fecha_modificacion
    private Persona persona;
    private Cargo cargo;
    private Sucursal sucursal;
    private Integer idUsuarioCreacion;// id_usuario_creacion
    private Integer idUsuarioModificacion;// id_usuario_modificacion

    public Empleado() {}
     
    public Empleado (Integer idEmpleado, BigDecimal salario, LocalDate fechaContratacion, LocalDate fechaBaja, Boolean activo, LocalDate fechaCreacion, LocalDate fechaModificacion, Persona persona, Cargo cargo, Sucursal sucursal, Integer idUsuarioCreacion, Integer idUsuarioModificacion) {
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

    public Integer getIdEmpleado() { return idEmpleado; }

    public void setIdEmpleado(Integer idEmpleado ){
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

    public Integer getIdUsuarioCreacion() { return idUsuarioCreacion; }

    public void setIdUsuarioCreacion(Integer idUsuarioCreacion ){
        this.idUsuarioCreacion = idUsuarioCreacion;
    }

    public Integer getIdUsuarioModificacion() { return idUsuarioModificacion; }

    public void setIdUsuarioModificacion(Integer idUsuarioModificacion ){
        this.idUsuarioModificacion = idUsuarioModificacion;
    }
}