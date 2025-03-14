package com.matasanos.model;

import java.math.BigDecimal;

public class ProductoCompra {

    private int idProductoCompra;
    private int cantidad;
    private BigDecimal costo;
    private int idCompra;
    private int idProducto;

    public ProductoCompra (int idProductoCompra, int cantidad, BigDecimal costo, int idCompra, int idProducto) {
        this.idProductoCompra = idProductoCompra;
        this.cantidad = cantidad;
        this.costo = costo;
        this.idCompra = idCompra;
        this.idProducto = idProducto;
    }

    public int getIdproductocompra() { return idProductoCompra; }

    public void setIdproductocompra(int idProductoCompra ){
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

    public int getIdcompra() { return idCompra; }

    public void setIdcompra(int idCompra ){
        this.idCompra = idCompra;
    }

    public int getIdproducto() { return idProducto; }

    public void setIdproducto(int idProducto ){
        this.idProducto = idProducto;
    }
}