package com.matasanos.model;



public class Categoria {

    private Integer idCategoria;// id_categoria
    private String nombreCategoria;// nombre_categoria
    private Departamento departamento;

    public Categoria() {}
     
    public Categoria (Integer idCategoria, String nombreCategoria, Departamento departamento) {
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
        this.departamento = departamento;
    }

    public Categoria (Integer idCategoria, String nombreCategoria) {
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
        this.departamento = null;
    }

    public Integer getIdCategoria() { return idCategoria; }

    public void setIdCategoria(Integer idCategoria ){
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