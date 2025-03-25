package com.matasanos.model;

import java.math.BigDecimal;

public class ProductoCompra {

    private int idProductoCompra;// id_producto_compra
    private int cantidad;// cantidad
    private BigDecimal costo;// costo
    private Compra compra;
    private Producto producto;

    public ProductoCompra() {}
     
    public ProductoCompra (int idProductoCompra, int cantidad, BigDecimal costo, Compra compra, Producto producto) {
        this.idProductoCompra = idProductoCompra;
        this.cantidad = cantidad;
        this.costo = costo;
        this.compra = compra;
        this.producto = producto;
    }

    public int getIdProductoCompra() { return idProductoCompra; }

    public void setIdProductoCompra(int idProductoCompra ){
        this.idProductoCompra = idProductoCompra;
    }

    public int getCantidad() { return cantidad; }

    public void setCantidad(int cantidad ){
        this.cantidad = cantidad;
    }

    public BigDecimal getCosto() { return costo; }

    public void setCosto(BigDecimal costo ){
        this.costo = costo;
    }

    public Compra getCompra() { return compra; }

    public void setCompra(Compra compra ){
        this.compra = compra;
    }

    public Producto getProducto() { return producto; }

    public void setProducto(Producto producto ){
        this.producto = producto;
    }
}