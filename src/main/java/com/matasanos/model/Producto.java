package com.matasanos.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Producto {

    private int idProducto;
    private String nombre;
    private String descripcion;
    private BigDecimal precioVenta;
    private int inventario;
    private LocalDate fechaVencimiento;
    private boolean ventaLibre;
    private BigDecimal precioDescuento;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;
    private int idCategoria;
    private int idProveedor;
    private int idUsuarioCreacion;
    private int idUsuarioModificacion;

    public Producto (int idProducto, String nombre, String descripcion, BigDecimal precioVenta, int inventario, LocalDate fechaVencimiento, boolean ventaLibre, BigDecimal precioDescuento, LocalDate fechaCreacion, LocalDate fechaModificacion, int idCategoria, int idProveedor, int idUsuarioCreacion, int idUsuarioModificacion) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.inventario = inventario;
        this.fechaVencimiento = fechaVencimiento;
        this.ventaLibre = ventaLibre;
        this.precioDescuento = precioDescuento;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.idCategoria = idCategoria;
        this.idProveedor = idProveedor;
        this.idUsuarioCreacion = idUsuarioCreacion;
        this.idUsuarioModificacion = idUsuarioModificacion;
    }

    public int getIdproducto() { return idProducto; }

    public void setIdproducto(int idProducto ){
        this.idProducto = idProducto;
    }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre ){
        this.nombre = nombre;
    }

    public String getDescripcion() { return descripcion; }

    public void setDescripcion(String descripcion ){
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecioventa() { return precioVenta; }

    public void setPrecioventa(BigDecimal precioVenta ){
        this.precioVenta = precioVenta;
    }

    public int getInventario() { return inventario; }

    public void setInventario(int inventario ){
        this.inventario = inventario;
    }

    public LocalDate getFechavencimiento() { return fechaVencimiento; }

    public void setFechavencimiento(LocalDate fechaVencimiento ){
        this.fechaVencimiento = fechaVencimiento;
    }

    public boolean getVentalibre() { return ventaLibre; }

    public void setVentalibre(boolean ventaLibre ){
        this.ventaLibre = ventaLibre;
    }

    public BigDecimal getPreciodescuento() { return precioDescuento; }

    public void setPreciodescuento(BigDecimal precioDescuento ){
        this.precioDescuento = precioDescuento;
    }

    public LocalDate getFechacreacion() { return fechaCreacion; }

    public void setFechacreacion(LocalDate fechaCreacion ){
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechamodificacion() { return fechaModificacion; }

    public void setFechamodificacion(LocalDate fechaModificacion ){
        this.fechaModificacion = fechaModificacion;
    }

    public int getIdcategoria() { return idCategoria; }

    public void setIdcategoria(int idCategoria ){
        this.idCategoria = idCategoria;
    }

    public int getIdproveedor() { return idProveedor; }

    public void setIdproveedor(int idProveedor ){
        this.idProveedor = idProveedor;
    }

    public int getIdusuariocreacion() { return idUsuarioCreacion; }

    public void setIdusuariocreacion(int idUsuarioCreacion ){
        this.idUsuarioCreacion = idUsuarioCreacion;
    }

    public int getIdusuariomodificacion() { return idUsuarioModificacion; }

    public void setIdusuariomodificacion(int idUsuarioModificacion ){
        this.idUsuarioModificacion = idUsuarioModificacion;
    }
}