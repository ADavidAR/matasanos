package com.matasanos.model;



public class RecetaProducto {

    private Integer idRecetaProducto;// id_receta_producto
    private Integer cantidad;// cantidad
    private String indicaciones;// indicaciones
    private Receta receta;
    private Producto producto;

    public RecetaProducto() {}
     
    public RecetaProducto (Integer idRecetaProducto, Integer cantidad, String indicaciones, Receta receta, Producto producto) {
        this.idRecetaProducto = idRecetaProducto;
        this.cantidad = cantidad;
        this.indicaciones = indicaciones;
        this.receta = receta;
        this.producto = producto;
    }

    public Integer getIdRecetaProducto() { return idRecetaProducto; }

    public void setIdRecetaProducto(Integer idRecetaProducto ){
        this.idRecetaProducto = idRecetaProducto;
    }

    public Integer getCantidad() { return cantidad; }

    public void setCantidad(Integer cantidad ){
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