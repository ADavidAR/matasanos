package com.matasanos.model;



public class RecetaProducto {

    private int idRecetaProducto;// id_receta_producto
    private int cantidad;// cantidad
    private Receta receta;
    private Producto producto;

    public RecetaProducto() {}
     
    public RecetaProducto (int idRecetaProducto, int cantidad, Receta receta, Producto producto) {
        this.idRecetaProducto = idRecetaProducto;
        this.cantidad = cantidad;
        this.receta = receta;
        this.producto = producto;
    }

    public int getIdrecetaproducto() { return idRecetaProducto; }

    public void setIdrecetaproducto(int idRecetaProducto ){
        this.idRecetaProducto = idRecetaProducto;
    }

    public int getCantidad() { return cantidad; }

    public void setCantidad(int cantidad ){
        this.cantidad = cantidad;
    }

    public Receta getReceta() { return receta; }

    public void setReceta(Receta receta ){
        this.receta = receta;
    }

    public Producto getProducto() { return producto; }

    public void setProducto(Producto producto ){
        this.producto = producto;
    }
}