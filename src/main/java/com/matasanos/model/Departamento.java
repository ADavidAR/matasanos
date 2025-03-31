package com.matasanos.model;



public class Departamento {

    private Integer idDepartamento;// id_departamento
    private String nombreDepartamento;// nombre_departamento

    public Departamento() {}
     
    public Departamento (Integer idDepartamento, String nombreDepartamento) {
        this.idDepartamento = idDepartamento;
        this.nombreDepartamento = nombreDepartamento;
    }

    public Integer getIdDepartamento() { return idDepartamento; }

    public void setIdDepartamento(Integer idDepartamento ){
        this.idDepartamento = idDepartamento;
    }

    public String getNombreDepartamento() { return nombreDepartamento; }

    public void setNombreDepartamento(String nombreDepartamento ){
        this.nombreDepartamento = nombreDepartamento;
    }
}