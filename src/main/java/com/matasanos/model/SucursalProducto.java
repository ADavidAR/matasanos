package com.matasanos.model;



public class SucursalProducto {

    private Integer idSucursalProducto;// id_sucursal_producto
    private int inventarioSucursal;// inventario_sucursal
    private Producto producto;
    private Sucursal sucursal;

    public SucursalProducto() {}
     
    public SucursalProducto (Integer idSucursalProducto, Integer inventarioSucursal, Producto producto, Sucursal sucursal) {
        this.idSucursalProducto = idSucursalProducto;
        this.inventarioSucursal = inventarioSucursal;
        this.producto = producto;
        this.sucursal = sucursal;
    }

    public Integer getIdSucursalProducto() { return idSucursalProducto; }

    public void setIdSucursalProducto(Integer idSucursalProducto ){
        this.idSucursalProducto = idSucursalProducto;
    }

    public Integer getInventarioSucursal() { return inventarioSucursal; }

    public void setInventarioSucursal(Integer inventarioSucursal ){
        this.inventarioSucursal = inventarioSucursal;
    }

    public Producto getProducto() { return producto; }

    public void setProducto(Producto producto ){
        this.producto = producto;
    }

    public Sucursal getSucursal() { return sucursal; }

    public void setSucursal(Sucursal sucursal ){
        this.sucursal = sucursal;
    }
}