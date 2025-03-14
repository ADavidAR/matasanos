package com.matasanos.model;



public class FuncionCargo {

    private int idFuncionCargo;
    private int idFuncion;
    private int idCargo;

    public FuncionCargo (int idFuncionCargo, int idFuncion, int idCargo) {
        this.idFuncionCargo = idFuncionCargo;
        this.idFuncion = idFuncion;
        this.idCargo = idCargo;
    }

    public int getIdfuncioncargo() { return idFuncionCargo; }

    public void setIdfuncioncargo(int idFuncionCargo ){
        this.idFuncionCargo = idFuncionCargo;
    }

    public int getIdfuncion() { return idFuncion; }

    public void setIdfuncion(int idFuncion ){
        this.idFuncion = idFuncion;
    }

    public int getIdcargo() { return idCargo; }

    public void setIdcargo(int idCargo ){
        this.idCargo = idCargo;
    }
}