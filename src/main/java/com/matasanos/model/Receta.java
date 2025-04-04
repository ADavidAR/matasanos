package com.matasanos.model;

import java.time.LocalDate;

public class Receta {

    private int idReceta;// id_receta
    private LocalDate fecha;// fecha
    private String descripcion;// descripcion
    private String nombreMedico;// nombre_medico
    private Cliente cliente;

    public Receta() {}
     
    public Receta (int idReceta, LocalDate fecha, String descripcion, String nombreMedico, Cliente cliente) {
        this.idReceta = idReceta;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.nombreMedico = nombreMedico;
        this.cliente = cliente;
    }

    public int getIdReceta() { return idReceta; }

    public void setIdReceta(int idReceta ){
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

    public String getNombreMedico() { return nombreMedico; }

    public void setNombreMedico(String nombreMedico ){
        this.nombreMedico = nombreMedico;
    }

    public Cliente getCliente() { return cliente; }

    public void setCliente(Cliente cliente ){
        this.cliente = cliente;
    }
}