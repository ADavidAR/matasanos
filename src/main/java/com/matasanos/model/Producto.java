package com.matasanos.model;

import java.time.LocalDate;
import java.math.BigDecimal;

public class Producto {

    private int idProducto;// id_producto
    private String nombreProducto;// nombre_producto
    private String descripcion;// descripcion
    private BigDecimal precioVenta;// precio_venta
    private int inventario;// inventario
    private LocalDate fechaVencimiento;// fecha_vencimiento
    private boolean ventaLibre;// venta_libre
    private BigDecimal precioDescuento;// precio_descuento
    private LocalDate fechaCreacion;// fecha_creacion
    private LocalDate fechaModificacion;// fecha_modificacion
    private Categoria categoria;
    private Proveedor proveedor;
    private int idUsuarioCreacion;// id_usuario_creacion
    private int idUsuarioModificacion;// id_usuario_modificacion

    public Producto() {}
     
    public Producto (int idProducto, String nombreProducto, String descripcion, BigDecimal precioVenta, int inventario, LocalDate fechaVencimiento, boolean ventaLibre, BigDecimal precioDescuento, LocalDate fechaCreacion, LocalDate fechaModificacion, Categoria categoria, Proveedor proveedor, int idUsuarioCreacion, int idUsuarioModificacion) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.inventario = inventario;
        this.fechaVencimiento = fechaVencimiento;
        this.ventaLibre = ventaLibre;
        this.precioDescuento = precioDescuento;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.categoria = categoria;
        this.proveedor = proveedor;
        this.idUsuarioCreacion = idUsuarioCreacion;
        this.idUsuarioModificacion = idUsuarioModificacion;
    }

    public int getIdProducto() { return idProducto; }

    public void setIdProducto(int idProducto ){
        this.idProducto = idProducto;
    }

    public String getNombreProducto() { return nombreProducto; }

    public void setNombreProducto(String nombreProducto ){
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcion() { return descripcion; }

    public void setDescripcion(String descripcion ){
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecioVenta() { return precioVenta; }

    public void setPrecioVenta(BigDecimal precioVenta ){
        this.precioVenta = precioVenta;
    }

    public int getInventario() { return inventario; }

    public void setInventario(int inventario ){
        this.inventario = inventario;
    }

    public LocalDate getFechaVencimiento() { return fechaVencimiento; }

    public void setFechaVencimiento(LocalDate fechaVencimiento ){
        this.fechaVencimiento = fechaVencimiento;
    }

    public boolean getVentaLibre() { return ventaLibre; }

    public void setVentaLibre(boolean ventaLibre ){
        this.ventaLibre = ventaLibre;
    }

    public BigDecimal getPrecioDescuento() { return precioDescuento; }

    public void setPrecioDescuento(BigDecimal precioDescuento ){
        this.precioDescuento = precioDescuento;
    }

    public LocalDate getFechaCreacion() { return fechaCreacion; }

    public void setFechaCreacion(LocalDate fechaCreacion ){
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaModificacion() { return fechaModificacion; }

    public void setFechaModificacion(LocalDate fechaModificacion ){
        this.fechaModificacion = fechaModificacion;
    }

    public Categoria getCategoria() { return categoria; }

    public void setCategoria(Categoria categoria ){
        this.categoria = categoria;
    }

    public Proveedor getProveedor() { return proveedor; }

    public void setProveedor(Proveedor proveedor ){
        this.proveedor = proveedor;
    }

    public int getIdUsuarioCreacion() { return idUsuarioCreacion; }

    public void setIdUsuarioCreacion(int idUsuarioCreacion ){
        this.idUsuarioCreacion = idUsuarioCreacion;
    }

    public int getIdUsuarioModificacion() { return idUsuarioModificacion; }

    public void setIdUsuarioModificacion(int idUsuarioModificacion ){
        this.idUsuarioModificacion = idUsuarioModificacion;
    }
}