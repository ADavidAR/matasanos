package com.matasanos.model;



public class Departamento {

    private int idDepartamento;// id_departamento
    private String nombreDepartamento;// nombre_departamento

    public Departamento() {}
     
    public Departamento (int idDepartamento, String nombreDepartamento) {
        this.idDepartamento = idDepartamento;
        this.nombreDepartamento = nombreDepartamento;
    }

    public int getIdDepartamento() { return idDepartamento; }

    public void setIdDepartamento(int idDepartamento ){
        this.idDepartamento = idDepartamento;
    }

    public String getNombreDepartamento() { return nombreDepartamento; }

    public void setNombreDepartamento(String nombreDepartamento ){
        this.nombreDepartamento = nombreDepartamento;
    }
}