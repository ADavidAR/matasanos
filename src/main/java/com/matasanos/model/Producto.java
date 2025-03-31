package com.matasanos.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Producto {

    private Integer idProducto;// id_producto
    private String nombreProducto;// nombre_producto
    private String descripcion;// descripcion
    private BigDecimal precioVenta;// precio_venta
    private LocalDate fechaVencimiento;// fecha_vencimiento
    private Boolean ventaLibre;// venta_libre
    private BigDecimal precioDescuento;// precio_descuento
    private BigDecimal impuesto;// impuesto
    private LocalDate fechaCreacion;// fecha_creacion
    private LocalDate fechaModificacion;// fecha_modificacion
    private BigDecimal costoVenta;// costo_venta
    private Categoria categoria;
    private Proveedor proveedor;
    private Integer inventario;
    private Integer idUsuarioCreacion;// id_usuario_creacion
    private Integer idUsuarioModificacion;// id_usuario_modificacion

    public Producto() {}
     
    public Producto (Integer idProducto, String nombreProducto, String descripcion, BigDecimal precioVenta, LocalDate fechaVencimiento, Boolean ventaLibre, BigDecimal precioDescuento, BigDecimal impuesto, LocalDate fechaCreacion, LocalDate fechaModificacion, BigDecimal costoVenta, Integer inventario, Categoria categoria, Proveedor proveedor, Integer idUsuarioCreacion, Integer idUsuarioModificacion) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.fechaVencimiento = fechaVencimiento;
        this.ventaLibre = ventaLibre;
        this.precioDescuento = precioDescuento;
        this.impuesto = impuesto;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.costoVenta = costoVenta;
        this.inventario = inventario;
        this.categoria = categoria;
        this.proveedor = proveedor;
        this.idUsuarioCreacion = idUsuarioCreacion;
        this.idUsuarioModificacion = idUsuarioModificacion;
    }

    public Integer getIdProducto() { return idProducto; }

    public void setIdProducto(Integer idProducto ){
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

    public LocalDate getFechaVencimiento() { return fechaVencimiento; }

    public void setFechaVencimiento(LocalDate fechaVencimiento ){
        this.fechaVencimiento = fechaVencimiento;
    }

    public Boolean getVentaLibre() { return ventaLibre; }

    public void setVentaLibre(Boolean ventaLibre ){
        this.ventaLibre = ventaLibre;
    }

    public BigDecimal getPrecioDescuento() { return precioDescuento; }

    public void setPrecioDescuento(BigDecimal precioDescuento ){
        this.precioDescuento = precioDescuento;
    }

    public BigDecimal getImpuesto() { return impuesto; }

    public void setImpuesto(BigDecimal impuesto ){
        this.impuesto = impuesto;
    }

    public LocalDate getFechaCreacion() { return fechaCreacion; }

    public void setFechaCreacion(LocalDate fechaCreacion ){
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaModificacion() { return fechaModificacion; }

    public void setFechaModificacion(LocalDate fechaModificacion ){
        this.fechaModificacion = fechaModificacion;
    }

    public BigDecimal getCostoVenta() { return costoVenta; }

    public void setCostoVenta(BigDecimal costoVenta ){
        this.costoVenta = costoVenta;
    }

    public Integer getInventario() {
        return inventario;
    }

    public void setInventario(Integer inventario) {
        this.inventario = inventario;
    }

    public Categoria getCategoria() { return categoria; }

    public void setCategoria(Categoria categoria ){
        this.categoria = categoria;
    }

    public Proveedor getProveedor() { return proveedor; }

    public void setProveedor(Proveedor proveedor ){
        this.proveedor = proveedor;
    }

    public Integer getIdUsuarioCreacion() { return idUsuarioCreacion; }

    public void setIdUsuarioCreacion(Integer idUsuarioCreacion ){
        this.idUsuarioCreacion = idUsuarioCreacion;
    }

    public Integer getIdUsuarioModificacion() { return idUsuarioModificacion; }

    public void setIdUsuarioModificacion(Integer idUsuarioModificacion ){
        this.idUsuarioModificacion = idUsuarioModificacion;
    }
}