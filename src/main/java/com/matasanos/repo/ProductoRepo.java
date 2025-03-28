package com.matasanos.repo;

import com.matasanos.model.Producto;
import com.matasanos.repo.rowmapper.CustomRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductoRepo {

    JdbcTemplate jdbcTemplate;

    public ProductoRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Producto obtenerProductoDeSucursal(int idProducto, int idSucursal) {
        String sql = "SELECT id_producto, nombre_producto, descripcion, precio_venta, fecha_vencimiento, venta_libre, precio_descuento, impuesto, fecha_creacion, fecha_modificacion, costo_venta, id_categoria, nombre_categoria, SUM(cantidad * factor) AS inventario_actual, id_usuario_creacion, id_usuario_creacion FROM v_ProductoSucursal WHERE id_producto = ? AND id_sucursal = ?";
        List<Producto> productos = jdbcTemplate.query(sql, CustomRowMapper.productoDeSucursalRowMapper, idProducto, idSucursal);

        return (productos.isEmpty()) ? null : productos.getFirst();
    }

    public Producto obtenerProducto(int idProducto) {
        String sql = "SELECT id_producto, nombre_producto, descripcion, precio_venta, fecha_vencimiento, venta_libre, precio_descuento, impuesto, fecha_creacion, fecha_modificacion, costo_venta, id_categoria, nombre_categoria, SUM(cantidad * factor) AS inventario_actual, id_proveedor, id_usuario_creacion, id_usuario_creacion FROM v_Producto WHERE id_producto = ?";
        List<Producto> productos = jdbcTemplate.query(sql, CustomRowMapper.productoRowMapper, idProducto);

        return (productos.isEmpty()) ? null : productos.getFirst();
    }

    public List<Producto> listarProductosDeSucursal(int idSucursal) {
        String sql = "SELECT id_producto, nombre_producto, descripcion, precio_venta, fecha_vencimiento, venta_libre, precio_descuento, impuesto, fecha_creacion, fecha_modificacion, costo_venta, id_categoria, nombre_categoria, SUM(cantidad * factor) AS inventario_actual, id_usuario_creacion, id_usuario_creacion FROM v_ProductoSucursal Where id_sucursal = ?";

        return  jdbcTemplate.query(sql, CustomRowMapper.productoDeSucursalRowMapper, idSucursal);
    }

    public List<Producto> listarProductos() {
        String sql = "SELECT id_producto, nombre_producto, descripcion, precio_venta, fecha_vencimiento, venta_libre, precio_descuento, impuesto, fecha_creacion, fecha_modificacion, costo_venta, id_categoria, nombre_categoria, SUM(cantidad * factor) AS inventario_actual, id_proveedor, id_usuario_creacion, id_usuario_creacion FROM v_Producto";

        return  jdbcTemplate.query(sql, CustomRowMapper.productoRowMapper);
    }

    public List<Producto> filtrarProductosPorNombre(String filtro) {
        String sql = "SELECT id_producto, nombre_producto, descripcion, precio_venta, fecha_vencimiento, venta_libre, precio_descuento, impuesto, fecha_creacion, fecha_modificacion, costo_venta, id_categoria, nombre_categoria, SUM(cantidad * factor) AS inventario_actual, id_proveedor, id_usuario_creacion, id_usuario_creacion FROM v_Producto WHERE LOWER(nombre_producto) LIKE ?";

        return  jdbcTemplate.query(sql, CustomRowMapper.productoRowMapper,
                    String.format("%%%s%%", filtro.toLowerCase())
        );
    }

    public List<Producto> filtrarProductosDeSucursalPorNombre(String filtro, int idSucursal) {
        String sql = "SELECT id_producto, nombre_producto, descripcion, precio_venta, fecha_vencimiento, venta_libre, precio_descuento, impuesto, fecha_creacion, fecha_modificacion, costo_venta, id_categoria, nombre_categoria, SUM(cantidad * factor) AS inventario_actual, id_usuario_creacion, id_usuario_creacion FROM v_ProductoSucursal WHERE id_sucursal = ? AND LOWER(nombre_producto) LIKE ?";

        return  jdbcTemplate.query(sql, CustomRowMapper.productoRowMapper,
                idSucursal,
                String.format("%%%s%%", filtro.toLowerCase())
        );
    }

}
