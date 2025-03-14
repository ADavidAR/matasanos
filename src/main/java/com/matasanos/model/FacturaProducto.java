package com.matasanos.model;

import java.math.BigDecimal;

public class FacturaProducto {

    private int idFacturaProducto;
    private int cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
    private int idFactura;
    private int idProducto;

    public FacturaProducto (int idFacturaProducto, int cantidad, BigDecimal precioUnitario, BigDecimal subtotal, int idFactura, int idProducto) {
        this.idFacturaProducto = idFacturaProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
        this.idFactura = idFactura;
        this.idProducto = idProducto;
    }

    public int getIdfacturaproducto() { return idFacturaProducto; }

    public void setIdfacturaproducto(int idFacturaProducto ){
        this.idFacturaProducto = idFacturaProducto;
    }

    public int getCantidad() { return cantidad; }

    public void setCantidad(int cantidad ){
        this.cantidad = cantidad;
    }

    public BigDecimal getPreciounitario() { return precioUnitario; }

    public void setPreciounitario(BigDecimal precioUnitario ){
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getSubtotal() { return subtotal; }

    public void setSubtotal(BigDecimal subtotal ){
        this.subtotal = subtotal;
    }

    public int getIdfactura() { return idFactura; }

    public void setIdfactura(int idFactura ){
        this.idFactura = idFactura;
    }

    public int getIdproducto() { return idProducto; }

    public void setIdproducto(int idProducto ){
        this.idProducto = idProducto;
    }
}