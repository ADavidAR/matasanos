package com.matasanos.model;

import java.math.BigDecimal;

public class FacturaProducto {

    private int idFacturaProducto;// id_factura_producto
    private int cantidad;// cantidad
    private BigDecimal precioUnitario;// precio_unitario
    private BigDecimal impusetPorcentaje;// impuset_porcentaje
    private BigDecimal impuesto;// impuesto
    private BigDecimal subtotal;// subtotal
    private Factura factura;
    private Producto producto;

    public FacturaProducto() {}
     
    public FacturaProducto (int idFacturaProducto, int cantidad, BigDecimal precioUnitario, BigDecimal impusetPorcentaje, BigDecimal impuesto, BigDecimal subtotal, Factura factura, Producto producto) {
        this.idFacturaProducto = idFacturaProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.impusetPorcentaje = impusetPorcentaje;
        this.impuesto = impuesto;
        this.subtotal = subtotal;
        this.factura = factura;
        this.producto = producto;
    }

    public int getIdFacturaProducto() { return idFacturaProducto; }

    public void setIdFacturaProducto(int idFacturaProducto ){
        this.idFacturaProducto = idFacturaProducto;
    }

    public int getCantidad() { return cantidad; }

    public void setCantidad(int cantidad ){
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() { return precioUnitario; }

    public void setPrecioUnitario(BigDecimal precioUnitario ){
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getImpusetPorcentaje() { return impusetPorcentaje; }

    public void setImpusetPorcentaje(BigDecimal impusetPorcentaje ){
        this.impusetPorcentaje = impusetPorcentaje;
    }

    public BigDecimal getImpuesto() { return impuesto; }

    public void setImpuesto(BigDecimal impuesto ){
        this.impuesto = impuesto;
    }

    public BigDecimal getSubtotal() { return subtotal; }

    public void setSubtotal(BigDecimal subtotal ){
        this.subtotal = subtotal;
    }

    public Factura getFactura() { return factura; }

    public void setFactura(Factura factura ){
        this.factura = factura;
    }

    public Producto getProducto() { return producto; }

    public void setProducto(Producto producto ){
        this.producto = producto;
    }
}