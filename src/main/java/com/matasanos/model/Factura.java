package com.matasanos.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Factura {

    private int idFactura;// id_factura
    private String numeroFactura;// numero_factura
    private LocalDate fechaEmision;// fecha_emision
    private String rtnCliente;// rtn_cliente
    private BigDecimal subtotal;// subtotal
    private BigDecimal impuesto;// impuesto
    private FacturacionSAR facturaSar;
    private Usuario usuario;
    private Cliente cliente;
    private Caja caja;

    public Factura() {}
     
    public Factura (int idFactura, String numeroFactura, LocalDate fechaEmision, String rtnCliente, BigDecimal subtotal, BigDecimal impuesto, FacturacionSAR facturaSar, Usuario usuario, Cliente cliente, Caja caja) {
        this.idFactura = idFactura;
        this.numeroFactura = numeroFactura;
        this.fechaEmision = fechaEmision;
        this.rtnCliente = rtnCliente;
        this.subtotal = subtotal;
        this.impuesto = impuesto;
        this.facturaSar = facturaSar;
        this.usuario = usuario;
        this.cliente = cliente;
        this.caja = caja;
    }

    public int getIdFactura() { return idFactura; }

    public void setIdFactura(int idFactura ){
        this.idFactura = idFactura;
    }

    public String getNumeroFactura() { return numeroFactura; }

    public void setNumeroFactura(String numeroFactura ){
        this.numeroFactura = numeroFactura;
    }

    public LocalDate getFechaEmision() { return fechaEmision; }

    public void setFechaEmision(LocalDate fechaEmision ){
        this.fechaEmision = fechaEmision;
    }

    public String getRtnCliente() { return rtnCliente; }

    public void setRtnCliente(String rtnCliente ){
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

    public FacturacionSAR getFacturaSar() { return facturaSar; }

    public void setFacturaSar(FacturacionSAR facturaSar ){
        this.facturaSar = facturaSar;
    }

    public Usuario getUsuario() { return usuario; }

    public void setUsuario(Usuario usuario ){
        this.usuario = usuario;
    }

    public Cliente getCliente() { return cliente; }

    public void setCliente(Cliente cliente ){
        this.cliente = cliente;
    }

    public Caja getCaja() { return caja; }

    public void setCaja(Caja caja ){
        this.caja = caja;
    }
}