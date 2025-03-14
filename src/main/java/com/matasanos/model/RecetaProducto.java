package com.matasanos.model;



public class RecetaProducto {

    private int idRecetaProducto;
    private int cantidad;
    private int idReceta;
    private int idProducto;

    public RecetaProducto (int idRecetaProducto, int cantidad, int idReceta, int idProducto) {
        this.idRecetaProducto = idRecetaProducto;
        this.cantidad = cantidad;
        this.idReceta = idReceta;
        this.idProducto = idProducto;
    }

    public int getIdrecetaproducto() { return idRecetaProducto; }

    public void setIdrecetaproducto(int idRecetaProducto ){
        this.idRecetaProducto = idRecetaProducto;
    }

    public int getCantidad() { return cantidad; }

    public void setCantidad(int cantidad ){
        this.cantidad = cantidad;
    }

    public int getIdreceta() { return idReceta; }

    public void setIdreceta(int idReceta ){
        this.idReceta = idReceta;
    }

    public int getIdproducto() { return idProducto; }

    public void setIdproducto(int idProducto ){
        this.idProducto = idProducto;
    }
}