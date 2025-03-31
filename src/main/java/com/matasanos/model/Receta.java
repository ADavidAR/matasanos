package com.matasanos.model;

import java.time.LocalDate;

public class Receta {

    private Integer idReceta;// id_receta
    private LocalDate fecha;// fecha
    private String descripcion;// descripcion
    private Medico medico;
    private Cliente cliente;

    public Receta() {}
     
    public Receta (Integer idReceta, LocalDate fecha, String descripcion, Medico medico, Cliente cliente) {
        this.idReceta = idReceta;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.medico = medico;
        this.cliente = cliente;
    }

    public Integer getIdReceta() { return idReceta; }

    public void setIdReceta(Integer idReceta ){
        this.idReceta = idReceta;
    }

    public LocalDate getFecha() { return fecha; }

    public void setFecha(LocalDate fecha ){
        this.fecha = fecha;
    }

    public String getDescripcion() { return descripcion; }

    public void setDescripcion(String descripcion ){
        this.descripcion = descripcion;
    }

    public Medico getMedico() { return medico; }

    public void setMedico(Medico medico ){
        this.medico = medico;
    }

    public Cliente getCliente() { return cliente; }

    public void setCliente(Cliente cliente ){
        this.cliente = cliente;
    }
}