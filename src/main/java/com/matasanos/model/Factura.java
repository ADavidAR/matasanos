package com.matasanos.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Factura {

    private int idFactura;
    private String numeroFactura;
    private LocalDate fechaEmision;
    private String rtnCliente;
    private BigDecimal subtotal;
    private BigDecimal impuesto;
    private BigDecimal total;
    private int idFacturaSar;
    private int idUsuario;
    private int idCliente;
    private int idCaja;

    public Factura (int idFactura, String numeroFactura, LocalDate fechaEmision, String rtnCliente, BigDecimal subtotal, BigDecimal impuesto, BigDecimal total, int idFacturaSar, int idUsuario, int idCliente, int idCaja) {
        this.idFactura = idFactura;
        this.numeroFactura = numeroFactura;
        this.fechaEmision = fechaEmision;
        this.rtnCliente = rtnCliente;
        this.subtotal = subtotal;
        this.impuesto = impuesto;
        this.total = total;
        this.idFacturaSar = idFacturaSar;
        this.idUsuario = idUsuario;
        this.idCliente = idCliente;
        this.idCaja = idCaja;
    }

    public int getIdfactura() { return idFactura; }

    public void setIdfactura(int idFactura ){
        this.idFactura = idFactura;
    }

    public String getNumerofactura() { return numeroFactura; }

    public void setNumerofactura(String numeroFactura ){
        this.numeroFactura = numeroFactura;
    }

    public LocalDate getFechaemision() { return fechaEmision; }

    public void setFechaemision(LocalDate fechaEmision ){
        this.fechaEmision = fechaEmision;
    }

    public String getRtncliente() { return rtnCliente; }

    public void setRtncliente(String rtnCliente ){
        this.rtnCliente = rtnCliente;
    }

    public BigDecimal getSubtotal() { return subtotal; }

    public void setSubtotal(BigDecimal subtotal ){
        this.subtotal = subtotal;
    }

    public BigDecimal getImpuesto() { return impuesto; }

    public void setImpuesto(BigDecimal impuesto ){
        this.impuesto = impuesto;
    }

    public BigDecimal getTotal() { return total; }

    public void setTotal(BigDecimal total ){
        this.total = total;
    }

    public int getIdfacturasar() { return idFacturaSar; }

    public void setIdfacturasar(int idFacturaSar ){
        this.idFacturaSar = idFacturaSar;
    }

    public int getIdusuario() { return idUsuario; }

    public void setIdusuario(int idUsuario ){
        this.idUsuario = idUsuario;
    }

    public int getIdcliente() { return idCliente; }

    public void setIdcliente(int idCliente ){
        this.idCliente = idCliente;
    }

    public int getIdcaja() { return idCaja; }

    public void setIdcaja(int idCaja ){
        this.idCaja = idCaja;
    }
}