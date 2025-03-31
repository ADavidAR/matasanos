package com.matasanos.model;



public class FuncionCargo {

    private Integer idFuncionCargo;// id_funcion_cargo
    private Funcion funcion;
    private Cargo cargo;

    public FuncionCargo() {}
     
    public FuncionCargo (Integer idFuncionCargo, Funcion funcion, Cargo cargo) {
        this.idFuncionCargo = idFuncionCargo;
        this.funcion = funcion;
        this.cargo = cargo;
    }

    public Integer getIdFuncionCargo() { return idFuncionCargo; }

    public void setIdFuncionCargo(Integer idFuncionCargo ){
        this.idFuncionCargo = idFuncionCargo;
    }

    public Funcion getFuncion() { return funcion; }

    public void setFuncion(Funcion funcion ){
        this.funcion = funcion;
    }

    public Cargo getCargo() { return cargo; }

    public void setCargo(Cargo cargo ){
        this.cargo = cargo;
    }
}