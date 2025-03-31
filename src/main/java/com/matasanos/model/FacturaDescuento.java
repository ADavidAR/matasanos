package com.matasanos.model;



public class FacturaDescuento {

    private Integer idFacturaDescuento;// id_factura_descuento
    private Factura factura;
    private Descuento descuento;

    public FacturaDescuento() {}
     
    public FacturaDescuento (Integer idFacturaDescuento, Factura factura, Descuento descuento) {
        this.idFacturaDescuento = idFacturaDescuento;
        this.factura = factura;
        this.descuento = descuento;
    }

    public Integer getIdFacturaDescuento() { return idFacturaDescuento; }

    public void setIdFacturaDescuento(Integer idFacturaDescuento ){
        this.idFacturaDescuento = idFacturaDescuento;
    }

    public Factura getFactura() { return factura; }

    public void setFactura(Factura factura ){
        this.factura = factura;
    }

    public Descuento getDescuento() { return descuento; }

    public void setDescuento(Descuento descuento ){
        this.descuento = descuento;
    }
}