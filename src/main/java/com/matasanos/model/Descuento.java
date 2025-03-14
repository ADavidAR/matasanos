package com.matasanos.model;

import java.math.BigDecimal;

public class Descuento {

    private int idDescuento;
    private String descuentoNombre;
    private BigDecimal porcentaje;

    public Descuento (int idDescuento, String descuentoNombre, BigDecimal porcentaje) {
        this.idDescuento = idDescuento;
        this.descuentoNombre = descuentoNombre;
        this.porcentaje = porcentaje;
    }

    public int getIddescuento() { return idDescuento; }

    public void setIddescuento(int idDescuento ){
        this.idDescuento = idDescuento;
    }

    public String getDescuentonombre() { return descuentoNombre; }

    public void setDescuentonombre(String descuentoNombre ){
        this.descuentoNombre = descuentoNombre;
    }

    public BigDecimal getPorcentaje() { return porcentaje; }

    public void setPorcentaje(BigDecimal porcentaje ){
        this.porcentaje = porcentaje;
    }
}