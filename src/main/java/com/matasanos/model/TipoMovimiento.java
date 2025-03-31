package com.matasanos.model;



public class TipoMovimiento {

    private Integer idTipoMovimiento;// id_tipo_movimiento
    private String nombre;// nombre
    private Integer factor;// factor

    public TipoMovimiento() {}
     
    public TipoMovimiento (Integer idTipoMovimiento, String nombre, Integer factor) {
        this.idTipoMovimiento = idTipoMovimiento;
        this.nombre = nombre;
        this.factor = factor;
    }

    public Integer getIdTipoMovimiento() { return idTipoMovimiento; }

    public void setIdTipoMovimiento(Integer idTipoMovimiento ){
        this.idTipoMovimiento = idTipoMovimiento;
    }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre ){
        this.nombre = nombre;
    }

    public Integer getFactor() { return factor; }

    public void setFactor(Integer factor ){
        this.factor = factor;
    }
}