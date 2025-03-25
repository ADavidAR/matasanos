package com.matasanos.model;

import java.time.LocalDate;
import java.math.BigDecimal;

public class Empleado {

    private int idEmpleado;// id_empleado
    private String primerNombre;// primer_nombre
    private String segundoNombre;// segundo_nombre
    private String primerApellido;// primer_apellido
    private String segundoApellido;// segundo_apellido
    private String telefono;// telefono
    private String correo;// correo
    private BigDecimal salario;// salario
    private LocalDate fechaContratacion;// fecha_contratacion
    private LocalDate fechaBaja;// fecha_baja
    private boolean activo;// activo
    private LocalDate fechaModificacion;// fecha_modificacion
    private Direcciones direccion;
    private Cargo cargo;
    private Usuario usuario;
    private Sucursal sucursal;
    private int idUsuarioCreacion;// id_usuario_creacion
    private int idUsuarioModificacion;// id_usuario_modificacion

    public Empleado() {}
     
    public Empleado (int idEmpleado, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String telefono, String correo, BigDecimal salario, LocalDate fechaContratacion, LocalDate fechaBaja, boolean activo, LocalDate fechaModificacion, Direcciones direccion, Cargo cargo, Usuario usuario, Sucursal sucursal, int idUsuarioCreacion, int idUsuarioModificacion) {
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
        this.direccion = direccion;
        this.cargo = cargo;
        this.usuario = usuario;
        this.sucursal = sucursal;
        this.idUsuarioCreacion = idUsuarioCreacion;
        this.idUsuarioModificacion = idUsuarioModificacion;
    }

    public int getIdEmpleado() { return idEmpleado; }

    public void setIdEmpleado(int idEmpleado ){
        this.idEmpleado = idEmpleado;
    }

    public String getPrimerNombre() { return primerNombre; }

    public void setPrimerNombre(String primerNombre ){
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() { return segundoNombre; }

    public void setSegundoNombre(String segundoNombre ){
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() { return primerApellido; }

    public void setPrimerApellido(String primerApellido ){
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() { return segundoApellido; }

    public void setSegundoApellido(String segundoApellido ){
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

    public LocalDate getFechaModificacion() { return fechaModificacion; }

    public void setFechaModificacion(LocalDate fechaModificacion ){
        this.fechaModificacion = fechaModificacion;
    }

    public Direcciones getDireccion() { return direccion; }

    public void setDireccion(Direcciones direccion ){
        this.direccion = direccion;
    }

    public Cargo getCargo() { return cargo; }

    public void setCargo(Cargo cargo ){
        this.cargo = cargo;
    }

    public Usuario getUsuario() { return usuario; }

    public void setUsuario(Usuario usuario ){
        this.usuario = usuario;
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