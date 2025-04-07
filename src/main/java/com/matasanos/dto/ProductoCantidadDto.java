package com.matasanos.dto;

public class ProductoCantidadDto {
    private int idProducto;
    private int cantidad;
    private int idSucursal;

    public int getIdSucursal() {
        return idSucursal;
    }
    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }
    public int getIdProducto() {
        return idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
