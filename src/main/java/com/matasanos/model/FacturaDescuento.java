package com.matasanos.model;



public class FacturaDescuento {

    private int idFacturaDescuento;
    private int idFactura;
    private int idDescuento;

    public FacturaDescuento (int idFacturaDescuento, int idFactura, int idDescuento) {
        this.idFacturaDescuento = idFacturaDescuento;
        this.idFactura = idFactura;
        this.idDescuento = idDescuento;
    }

    public int getIdfacturadescuento() { return idFacturaDescuento; }

    public void setIdfacturadescuento(int idFacturaDescuento ){
        this.idFacturaDescuento = idFacturaDescuento;
    }

    public int getIdfactura() { return idFactura; }

    public void setIdfactura(int idFactura ){
        this.idFactura = idFactura;
    }

    public int getIddescuento() { return idDescuento; }

    public void setIddescuento(int idDescuento ){
        this.idDescuento = idDescuento;
    }
}