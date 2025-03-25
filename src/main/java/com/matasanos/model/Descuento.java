package com.matasanos.model;

import java.math.BigDecimal;

public class Descuento {

    private int idDescuento;// id_descuento
    private String descuentoNombre;// descuento_nombre
    private BigDecimal porcentaje;// porcentaje

    public Descuento() {}
     
    public Descuento (int idDescuento, String descuentoNombre, BigDecimal porcentaje) {
        this.idDescuento = idDescuento;
        this.descuentoNombre = descuentoNombre;
        this.porcentaje = porcentaje;
    }

    public int getIdDescuento() { return idDescuento; }

    public void setIdDescuento(int idDescuento ){
        this.idDescuento = idDescuento;
    }

    public String getDescuentoNombre() { return descuentoNombre; }

    public void setDescuentoNombre(String descuentoNombre ){
        this.descuentoNombre = descuentoNombre;
    }

    public BigDecimal getPorcentaje() { return porcentaje; }

    public void setPorcentaje(BigDecimal porcentaje ){
        this.porcentaje = porcentaje;
    }
}