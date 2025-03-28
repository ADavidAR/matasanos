package com.matasanos.model;



public class TipoMovimiento {

    private int idTipoMovimiento;// id_tipo_movimiento
    private String nombre;// nombre
    private int factor;// factor

    public TipoMovimiento() {}
     
    public TipoMovimiento (int idTipoMovimiento, String nombre, int factor) {
        this.idTipoMovimiento = idTipoMovimiento;
        this.nombre = nombre;
        this.factor = factor;
    }

    public int getIdTipoMovimiento() { return idTipoMovimiento; }

    public void setIdTipoMovimiento(int idTipoMovimiento ){
        this.idTipoMovimiento = idTipoMovimiento;
    }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre ){
        this.nombre = nombre;
    }

    public int getFactor() { return factor; }

    public void setFactor(int factor ){
        this.factor = factor;
    }
}