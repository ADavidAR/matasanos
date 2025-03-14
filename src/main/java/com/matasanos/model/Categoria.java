package com.matasanos.model;



public class Categoria {

    private int idCategoria;
    private String nombreCategoria;
    private int idDepartamento;

    public Categoria (int idCategoria, String nombreCategoria, int idDepartamento) {
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
        this.idDepartamento = idDepartamento;
    }

    public int getIdcategoria() { return idCategoria; }

    public void setIdcategoria(int idCategoria ){
        this.idCategoria = idCategoria;
    }

    public String getNombrecategoria() { return nombreCategoria; }

    public void setNombrecategoria(String nombreCategoria ){
        this.nombreCategoria = nombreCategoria;
    }

    public int getIddepartamento() { return idDepartamento; }

    public void setIddepartamento(int idDepartamento ){
        this.idDepartamento = idDepartamento;
    }
}