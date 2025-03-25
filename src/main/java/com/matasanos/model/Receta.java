package com.matasanos.model;

import java.time.LocalDate;

public class Receta {

    private int idReceta;// id_receta
    private Medico medico;
    private LocalDate fecha;// fecha
    private String descripcion;// descripcion
    private String indicaciones;// indicaciones
    private Cliente cliente;

    public Receta() {}
     
    public Receta (int idReceta, Medico medico, LocalDate fecha, String descripcion, String indicaciones, Cliente cliente) {
        this.idReceta = idReceta;
        this.medico = medico;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.indicaciones = indicaciones;
        this.cliente = cliente;
    }

    public int getIdReceta() { return idReceta; }

    public void setIdReceta(int idReceta ){
        this.idReceta = idReceta;
    }

    public Medico getMedico() { return medico; }

    public void setMedico(Medico medico ){
        this.medico = medico;
    }

    public LocalDate getFecha() { return fecha; }

    public void setFecha(LocalDate fecha ){
        this.fecha = fecha;
    }

    public String getDescripcion() { return descripcion; }

    public void setDescripcion(String descripcion ){
        this.descripcion = descripcion;
    }

    public String getIndicaciones() { return indicaciones; }

    public void setIndicaciones(String indicaciones ){
        this.indicaciones = indicaciones;
    }

    public Cliente getCliente() { return cliente; }

    public void setCliente(Cliente cliente ){
        this.cliente = cliente;
    }
}