package com.matasanos.model;



public class Categoria {

    private int idCategoria;// id_categoria
    private String nombreCategoria;// nombre_categoria
    private Departamento departamento;

    public Categoria (int idCategoria, String nombreCategoria, Departamento departamento) {
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
        this.departamento = departamento;
    }

    public int getIdcategoria() { return idCategoria; }

    public void setIdcategoria(int idCategoria ){
        this.idCategoria = idCategoria;
    }

    public String getNombrecategoria() { return nombreCategoria; }

    public void setNombrecategoria(String nombreCategoria ){
        this.nombreCategoria = nombreCategoria;
    }

    public Departamento getDepartamento() { return departamento; }

    public void setDepartamento(Departamento departamento ){
        this.departamento = departamento;
    }
}