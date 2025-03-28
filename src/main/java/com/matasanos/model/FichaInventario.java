package com.matasanos.model;

import java.time.LocalDate;

public class FichaInventario {

    private int idFicha;// id_ficha
    private int cantidad;// cantidad
    private String referencia;// referencia
    private LocalDate fecha;// fecha
    private Producto producto;
    private Sucursal sucursal;
    private TipoMovimiento tipoMovimiento;

    public FichaInventario() {}
     
    public FichaInventario (int idFicha, int cantidad, String referencia, LocalDate fecha, Producto producto, Sucursal sucursal, TipoMovimiento tipoMovimiento) {
        this.idFicha = idFicha;
        this.cantidad = cantidad;
        this.referencia = referencia;
        this.fecha = fecha;
        this.producto = producto;
        this.sucursal = sucursal;
        this.tipoMovimiento = tipoMovimiento;
    }

    public int getIdFicha() { return idFicha; }

    public void setIdFicha(int idFicha ){
        this.idFicha = idFicha;
    }

    public int getCantidad() { return cantidad; }

    public void setCantidad(int cantidad ){
        this.cantidad = cantidad;
    }

    public String getReferencia() { return referencia; }

    public void setReferencia(String referencia ){
        this.referencia = referencia;
    }

    public LocalDate getFecha() { return fecha; }

    public void setFecha(LocalDate fecha ){
        this.fecha = fecha;
    }

    public Producto getProducto() { return producto; }

    public void setProducto(Producto producto ){
        this.producto = producto;
    }

    public Sucursal getSucursal() { return sucursal; }

    public void setSucursal(Sucursal sucursal ){
        this.sucursal = sucursal;
    }

    public TipoMovimiento getTipoMovimiento() { return tipoMovimiento; }

    public void setTipoMovimiento(TipoMovimiento tipoMovimiento ){
        this.tipoMovimiento = tipoMovimiento;
    }
}