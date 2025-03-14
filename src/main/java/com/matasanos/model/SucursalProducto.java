package com.matasanos.model;



public class SucursalProducto {

    private int idSucursalProducto;
    private int inventarioSucursal;
    private int idProducto;
    private int idSucursal;

    public SucursalProducto (int idSucursalProducto, int inventarioSucursal, int idProducto, int idSucursal) {
        this.idSucursalProducto = idSucursalProducto;
        this.inventarioSucursal = inventarioSucursal;
        this.idProducto = idProducto;
        this.idSucursal = idSucursal;
    }

    public int getIdsucursalproducto() { return idSucursalProducto; }

    public void setIdsucursalproducto(int idSucursalProducto ){
        this.idSucursalProducto = idSucursalProducto;
    }

    public int getInventariosucursal() { return inventarioSucursal; }

    public void setInventariosucursal(int inventarioSucursal ){
        this.inventarioSucursal = inventarioSucursal;
    }

    public int getIdproducto() { return idProducto; }

    public void setIdproducto(int idProducto ){
        this.idProducto = idProducto;
    }

    public int getIdsucursal() { return idSucursal; }

    public void setIdsucursal(int idSucursal ){
        this.idSucursal = idSucursal;
    }
}