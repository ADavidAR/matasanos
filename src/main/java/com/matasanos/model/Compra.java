package com.matasanos.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Compra {

    private int idCompra;// id_compra
    private String numFacturaCompra;// num_factura_compra
    private LocalDate fechaCompra;// fecha_compra
    private LocalDate fechaEntrega;// fecha_entrega
    private BigDecimal costoTotal;// costo_total
    private Proveedor proveedor;

    public Compra() {}
     
    public Compra (int idCompra, String numFacturaCompra, LocalDate fechaCompra, LocalDate fechaEntrega, BigDecimal costoTotal, Proveedor proveedor) {
        this.idCompra = idCompra;
        this.numFacturaCompra = numFacturaCompra;
        this.fechaCompra = fechaCompra;
        this.fechaEntrega = fechaEntrega;
        this.costoTotal = costoTotal;
        this.proveedor = proveedor;
    }

    public int getIdCompra() { return idCompra; }

    public void setIdCompra(int idCompra ){
        this.idCompra = idCompra;
    }

    public String getNumFacturaCompra() { return numFacturaCompra; }

    public void setNumFacturaCompra(String numFacturaCompra ){
        this.numFacturaCompra = numFacturaCompra;
    }

    public LocalDate getFechaCompra() { return fechaCompra; }

    public void setFechaCompra(LocalDate fechaCompra ){
        this.fechaCompra = fechaCompra;
    }

    public LocalDate getFechaEntrega() { return fechaEntrega; }

    public void setFechaEntrega(LocalDate fechaEntrega ){
        this.fechaEntrega = fechaEntrega;
    }

    public BigDecimal getCostoTotal() { return costoTotal; }

    public void setCostoTotal(BigDecimal costoTotal ){
        this.costoTotal = costoTotal;
    }

    public Proveedor getProveedor() { return proveedor; }

    public void setProveedor(Proveedor proveedor ){
        this.proveedor = proveedor;
    }
}