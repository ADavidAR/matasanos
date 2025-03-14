package com.matasanos.model;

import java.time.LocalDate;

public class Receta {

    private int idReceta;
    private String nombreMedico;
    private LocalDate fecha;
    private String descripcion;
    private String indicaciones;
    private int idCliente;

    public Receta (int idReceta, String nombreMedico, LocalDate fecha, String descripcion, String indicaciones, int idCliente) {
        this.idReceta = idReceta;
        this.nombreMedico = nombreMedico;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.indicaciones = indicaciones;
        this.idCliente = idCliente;
    }

    public int getIdreceta() { return idReceta; }

    public void setIdreceta(int idReceta ){
        this.idReceta = idReceta;
    }

    public String getNombremedico() { return nombreMedico; }

    public void setNombremedico(String nombreMedico ){
        this.nombreMedico = nombreMedico;
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

    public int getIdcliente() { return idCliente; }

    public void setIdcliente(int idCliente ){
        this.idCliente = idCliente;
    }
}