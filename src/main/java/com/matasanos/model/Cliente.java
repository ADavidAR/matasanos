package com.matasanos.model;

import java.time.LocalDate;

public class Cliente {

    private int idCliente;// id_cliente
    private String primerNombre;// primer_nombre
    private String segundoNombre;// segundo_nombre
    private String primerApellido;// primer_apellido
    private String segundoApellido;// segundo_apellido
    private String telefono;// telefono
    private String correo;// correo
    private String rtn;// rtn
    private String cedula;// cedula
    private LocalDate fechaCreacion;// fecha_creacion
    private LocalDate fechaModificacion;// fecha_modificacion
    private Direcciones direccion;
    private int idUsuarioCreacion;// id_usuario_creacion
    private int idUsuarioModificacion;// id_usuario_modificacion

    public Cliente() {}
     
    public Cliente (int idCliente, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String telefono, String correo, String rtn, String cedula, LocalDate fechaCreacion, LocalDate fechaModificacion, Direcciones direccion, int idUsuarioCreacion, int idUsuarioModificacion) {
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
        this.direccion = direccion;
        this.idUsuarioCreacion = idUsuarioCreacion;
        this.idUsuarioModificacion = idUsuarioModificacion;
    }

    public int getIdCliente() { return idCliente; }

    public void setIdCliente(int idCliente ){
        this.idCliente = idCliente;
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

    public String getRtn() { return rtn; }

    public void setRtn(String rtn ){
        this.rtn = rtn;
    }

    public String getCedula() { return cedula; }

    public void setCedula(String cedula ){
        this.cedula = cedula;
    }

    public LocalDate getFechaCreacion() { return fechaCreacion; }

    public void setFechaCreacion(LocalDate fechaCreacion ){
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaModificacion() { return fechaModificacion; }

    public void setFechaModificacion(LocalDate fechaModificacion ){
        this.fechaModificacion = fechaModificacion;
    }

    public Direcciones getDireccion() { return direccion; }

    public void setDireccion(Direcciones direccion ){
        this.direccion = direccion;
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