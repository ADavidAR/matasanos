package com.matasanos.model;



public class Categoria {

    private int idCategoria;// id_categoria
    private String nombreCategoria;// nombre_categoria
    private Departamento departamento;

    public Categoria() {}
     
    public Categoria (int idCategoria, String nombreCategoria, Departamento departamento) {
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
        this.departamento = departamento;
    }

    public int getIdCategoria() { return idCategoria; }

    public void setIdCategoria(int idCategoria ){
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() { return nombreCategoria; }

    public void setNombreCategoria(String nombreCategoria ){
        this.nombreCategoria = nombreCategoria;
    }

    public Departamento getDepartamento() { return departamento; }

    public void setDepartamento(Departamento departamento ){
        this.departamento = departamento;
    }
}