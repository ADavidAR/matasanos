package com.matasanos.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Compra {

    private int idCompra;
    private String numFacturaCompra;
    private LocalDate fechaCompra;
    private LocalDate fechaEntrega;
    private BigDecimal costoTotal;
    private int idProveedor;

    public Compra (int idCompra, String numFacturaCompra, LocalDate fechaCompra, LocalDate fechaEntrega, BigDecimal costoTotal, int idProveedor) {
        this.idCompra = idCompra;
        this.numFacturaCompra = numFacturaCompra;
        this.fechaCompra = fechaCompra;
        this.fechaEntrega = fechaEntrega;
        this.costoTotal = costoTotal;
        this.idProveedor = idProveedor;
    }

    public int getIdcompra() { return idCompra; }

    public void setIdcompra(int idCompra ){
        this.idCompra = idCompra;
    }

    public String getNumfacturacompra() { return numFacturaCompra; }

    public void setNumfacturacompra(String numFacturaCompra ){
        this.numFacturaCompra = numFacturaCompra;
    }

    public LocalDate getFechacompra() { return fechaCompra; }

    public void setFechacompra(LocalDate fechaCompra ){
        this.fechaCompra = fechaCompra;
    }

    public LocalDate getFechaentrega() { return fechaEntrega; }

    public void setFechaentrega(LocalDate fechaEntrega ){
        this.fechaEntrega = fechaEntrega;
    }

    public BigDecimal getCostototal() { return costoTotal; }

    public void setCostototal(BigDecimal costoTotal ){
        this.costoTotal = costoTotal;
    }

    public int getIdproveedor() { return idProveedor; }

    public void setIdproveedor(int idProveedor ){
        this.idProveedor = idProveedor;
    }
}