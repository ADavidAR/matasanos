package com.matasanos.model;

import java.math.BigDecimal;

public class FacturaProducto {

    private Integer idFacturaProducto;// id_factura_producto
    private Integer cantidad;// cantidad
    private BigDecimal precioUnitario;// precio_unitario
    private BigDecimal impusetPorcentaje;// impuset_porcentaje
    private BigDecimal impuesto;// impuesto
    private BigDecimal subtotal;// subtotal
    private Factura factura;
    private Producto producto;

    public FacturaProducto() {}
     
    public FacturaProducto (Integer idFacturaProducto, Integer cantidad, BigDecimal precioUnitario, BigDecimal impusetPorcentaje, BigDecimal impuesto, BigDecimal subtotal, Factura factura, Producto producto) {
        this.idFacturaProducto = idFacturaProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.impusetPorcentaje = impusetPorcentaje;
        this.impuesto = impuesto;
        this.subtotal = subtotal;
        this.factura = factura;
        this.producto = producto;
    }

    public Integer getIdFacturaProducto() { return idFacturaProducto; }

    public void setIdFacturaProducto(Integer idFacturaProducto ){
        this.idFacturaProducto = idFacturaProducto;
    }

    public Integer getCantidad() { return cantidad; }

    public void setCantidad(Integer cantidad ){
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