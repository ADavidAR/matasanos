package com.matasanos.model;

import java.time.LocalDate;

public class Cliente {

    private int idCliente;// id_cliente
    private String rtn;// rtn
    private LocalDate fechaCreacion;// fecha_creacion
    private LocalDate fechaModificacion;// fecha_modificacion
    private Persona persona;
    private int idUsuarioCreacion;// id_usuario_creacion
    private int idUsuarioModificacion;// id_usuario_modificacion

    public Cliente() {}
     
    public Cliente (int idCliente, String rtn, LocalDate fechaCreacion, LocalDate fechaModificacion, Persona persona, int idUsuarioCreacion, int idUsuarioModificacion) {
        this.idCliente = idCliente;
        this.rtn = rtn;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.persona = persona;
        this.idUsuarioCreacion = idUsuarioCreacion;
        this.idUsuarioModificacion = idUsuarioModificacion;
    }

    public int getIdCliente() { return idCliente; }

    public void setIdCliente(int idCliente ){
        this.idCliente = idCliente;
    }

    public String getRtn() { return rtn; }

    public void setRtn(String rtn ){
        this.rtn = rtn;
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

    public int getIdUsuarioCreacion() { return idUsuarioCreacion; }

    public void setIdUsuarioCreacion(int idUsuarioCreacion ){
        this.idUsuarioCreacion = idUsuarioCreacion;
    }

    public int getIdUsuarioModificacion() { return idUsuarioModificacion; }

    public void setIdUsuarioModificacion(int idUsuarioModificacion ){
        this.idUsuarioModificacion = idUsuarioModificacion;
    }
}