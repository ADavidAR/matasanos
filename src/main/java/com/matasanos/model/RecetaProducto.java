package com.matasanos.model;



public class RecetaProducto {

    private int idRecetaProducto;// id_receta_producto
    private int cantidad;// cantidad
    private String indicaciones;// indicaciones
    private Receta receta;
    private Producto producto;

    public RecetaProducto() {}
     
    public RecetaProducto (int idRecetaProducto, int cantidad, String indicaciones, Receta receta, Producto producto) {
        this.idRecetaProducto = idRecetaProducto;
        this.cantidad = cantidad;
        this.indicaciones = indicaciones;
        this.receta = receta;
        this.producto = producto;
    }

    public int getIdRecetaProducto() { return idRecetaProducto; }

    public void setIdRecetaProducto(int idRecetaProducto ){
        this.idRecetaProducto = idRecetaProducto;
    }

    public int getCantidad() { return cantidad; }

    public void setCantidad(int cantidad ){
        this.cantidad = cantidad;
    }

    public String getIndicaciones() { return indicaciones; }

    public void setIndicaciones(String indicaciones ){
        this.indicaciones = indicaciones;
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