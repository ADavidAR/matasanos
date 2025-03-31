package com.matasanos.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Pago {

    private Integer idPago;// id_pago
    private BigDecimal importe;// importe
    private LocalDate fecha;// fecha
    private Factura factura;
    private MetodoPago metodo;

    public Pago() {}
     
    public Pago (Integer idPago, BigDecimal importe, LocalDate fecha, Factura factura, MetodoPago metodo) {
        this.idPago = idPago;
        this.importe = importe;
        this.fecha = fecha;
        this.factura = factura;
        this.metodo = metodo;
    }

    public Integer getIdPago() { return idPago; }

    public void setIdPago(Integer idPago ){
        this.idPago = idPago;
    }

    public BigDecimal getImporte() { return importe; }

    public void setImporte(BigDecimal importe ){
        this.importe = importe;
    }

    public LocalDate getFecha() { return fecha; }

    public void setFecha(LocalDate fecha ){
        this.fecha = fecha;
    }

    public Factura getFactura() { return factura; }

    public void setFactura(Factura factura ){
        this.factura = factura;
    }

    public MetodoPago getMetodo() { return metodo; }

    public void setMetodo(MetodoPago metodo ){
        this.metodo = metodo;
    }
}