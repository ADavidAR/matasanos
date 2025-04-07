package com.matasanos.model;

import java.time.LocalDate;
import java.util.List;

public class Cliente {

    private Integer idCliente;// id_cliente
    private String rtn;// rtn
    private LocalDate fechaCreacion;// fecha_creacion
    private LocalDate fechaModificacion;// fecha_modificacion
    private Persona persona;
    private Integer idUsuarioCreacion;// id_usuario_creacion
    private Integer idUsuarioModificacion;// id_usuario_modificacion
    private List<Correo> correos;
    private List<Telefono>  telefonos;

    public Cliente() {}
     
    public Cliente (Integer idCliente, String rtn, LocalDate fechaCreacion, LocalDate fechaModificacion, Persona persona, Integer idUsuarioCreacion, Integer idUsuarioModificacion) {
        this.idCliente = idCliente;
        this.rtn = rtn;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.persona = persona;
        this.idUsuarioCreacion = idUsuarioCreacion;
        this.idUsuarioModificacion = idUsuarioModificacion;
    }

    public Integer getIdCliente() { return idCliente; }

    public void setIdCliente(Integer idCliente ){
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

    public Integer getIdUsuarioCreacion() { return idUsuarioCreacion; }

    public void setIdUsuarioCreacion(Integer idUsuarioCreacion ){
        this.idUsuarioCreacion = idUsuarioCreacion;
    }

    public Integer getIdUsuarioModificacion() { return idUsuarioModificacion; }

    public void setIdUsuarioModificacion(Integer idUsuarioModificacion ){
        this.idUsuarioModificacion = idUsuarioModificacion;
    }

    public List<Correo> getCorreos() {
        return correos;
    }

    public void setCorreos(List<Correo> correos) {
        this.correos = correos;
    }

    public List<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<Telefono> telefonos) {
        this.telefonos = telefonos;
    }
}