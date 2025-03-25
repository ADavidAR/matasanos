package com.matasanos.model;



public class SucursalProducto {

    private int idSucursalProducto;// id_sucursal_producto
    private int inventarioSucursal;// inventario_sucursal
    private Producto producto;
    private Sucursal sucursal;

    public SucursalProducto() {}
     
    public SucursalProducto (int idSucursalProducto, int inventarioSucursal, Producto producto, Sucursal sucursal) {
        this.idSucursalProducto = idSucursalProducto;
        this.inventarioSucursal = inventarioSucursal;
        this.producto = producto;
        this.sucursal = sucursal;
    }

    public int getIdSucursalProducto() { return idSucursalProducto; }

    public void setIdSucursalProducto(int idSucursalProducto ){
        this.idSucursalProducto = idSucursalProducto;
    }

    public int getInventarioSucursal() { return inventarioSucursal; }

    public void setInventarioSucursal(int inventarioSucursal ){
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