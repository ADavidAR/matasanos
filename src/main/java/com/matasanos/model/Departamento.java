package com.matasanos.model;



public class Departamento {

    private int idDepartamento;// id_departamento
    private String nombreDepartamento;// nombre_departamento

    public Departamento (int idDepartamento, String nombreDepartamento) {
        this.idDepartamento = idDepartamento;
        this.nombreDepartamento = nombreDepartamento;
    }

    public int getIddepartamento() { return idDepartamento; }

    public void setIddepartamento(int idDepartamento ){
        this.idDepartamento = idDepartamento;
    }

    public String getNombredepartamento() { return nombreDepartamento; }

    public void setNombredepartamento(String nombreDepartamento ){
        this.nombreDepartamento = nombreDepartamento;
    }
}