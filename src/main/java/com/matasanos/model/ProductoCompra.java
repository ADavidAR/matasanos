package com.matasanos.model;

import java.math.BigDecimal;

public class ProductoCompra {

    private Integer idProductoCompra;// id_producto_compra
    private Integer cantidad;// cantidad
    private BigDecimal costo;// costo
    private Compra compra;
    private Producto producto;

    public ProductoCompra() {}
     
    public ProductoCompra (Integer idProductoCompra, Integer cantidad, BigDecimal costo, Compra compra, Producto producto) {
        this.idProductoCompra = idProductoCompra;
        this.cantidad = cantidad;
        this.costo = costo;
        this.compra = compra;
        this.producto = producto;
    }

    public Integer getIdProductoCompra() { return idProductoCompra; }

    public void setIdProductoCompra(Integer idProductoCompra ){
        this.idProductoCompra = idProductoCompra;
    }

    public Integer getCantidad() { return cantidad; }

    public void setCantidad(Integer cantidad ){
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