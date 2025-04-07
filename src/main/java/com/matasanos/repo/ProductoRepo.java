package com.matasanos.repo;

import ch.qos.logback.core.testUtil.AbstractMultiThreadedHarness;
import com.matasanos.model.FichaInventario;
import com.matasanos.model.Producto;
import com.matasanos.repo.rowmapper.CustomRowMapper;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.matasanos.model.Producto;
import com.matasanos.repo.rowmapper.CustomRowMapper;

@Repository
public class ProductoRepo {

    JdbcTemplate jdbcTemplate;

    public ProductoRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Producto obtenerProductoDeSucursal(int idProducto, int idSucursal) {
        String sql = "SELECT id_producto, nombre_producto, descripcion, precio_venta, fecha_vencimiento, venta_libre, precio_descuento, impuesto, fecha_creacion, fecha_modificacion, costo_venta, id_categoria, nombre_categoria, id_departamento, nombre_departamento, SUM(cantidad * factor) AS inventario_actual, id_proveedor, id_usuario_creacion, id_usuario_modificacion, costo_venta, fecha_modificacion, precio_descuento FROM v_ProductoSucursal WHERE id_producto = ? AND id_sucursal = ? GROUP BY id_producto, nombre_producto, descripcion, precio_venta, fecha_vencimiento, venta_libre, precio_descuento, impuesto, fecha_creacion, fecha_modificacion, costo_venta, id_categoria, nombre_categoria, id_departamento, nombre_departamento, id_proveedor, id_usuario_creacion, id_usuario_modificacion, costo_venta, fecha_modificacion, precio_descuento";
        List<Producto> productos = jdbcTemplate.query(sql, CustomRowMapper.productoDeSucursalRowMapper, idProducto, idSucursal);

        return (productos.isEmpty()) ? null : productos.getFirst();
    }

    public Producto obtenerProducto(int idProducto) {
        String sql = "SELECT id_producto, nombre_producto, descripcion, precio_venta, fecha_vencimiento, venta_libre, precio_descuento, impuesto, fecha_creacion, fecha_modificacion, costo_venta, id_categoria, nombre_categoria, SUM(cantidad * factor) AS inventario_actual, id_proveedor, id_usuario_creacion, id_usuario_creacion FROM v_Producto WHERE id_producto = ?";
        List<Producto> productos = jdbcTemplate.query(sql, CustomRowMapper.productoRowMapper, idProducto);

        return (productos.isEmpty()) ? null : productos.getFirst();
    }

    public List<Producto> listarProductosDeSucursal(int idSucursal) {
        String sql = "SELECT id_producto, nombre_producto, descripcion, precio_venta, fecha_vencimiento, venta_libre, precio_descuento, impuesto, fecha_creacion, fecha_modificacion, costo_venta, id_categoria, nombre_categoria, id_departamento, nombre_departamento, SUM(cantidad * factor) AS inventario_actual, id_proveedor, id_usuario_creacion, id_usuario_modificacion FROM v_ProductoSucursal WHERE id_sucursal = ? GROUP BY id_producto, nombre_producto, descripcion, precio_venta, fecha_vencimiento, venta_libre, precio_descuento, impuesto, fecha_creacion, fecha_modificacion, costo_venta, id_categoria, nombre_categoria, id_departamento, nombre_departamento, id_proveedor, id_usuario_creacion, id_usuario_modificacion";

        return  jdbcTemplate.query(sql, CustomRowMapper.productoDeSucursalRowMapper, idSucursal);
    }

    public List<Producto> listarProductos() {
        String sql = "SELECT id_producto, nombre_producto, descripcion, precio_venta, fecha_vencimiento, venta_libre, precio_descuento, impuesto, fecha_creacion, fecha_modificacion, costo_venta, id_categoria, nombre_categoria, id_departamento, nombre_departamento, SUM(cantidad * factor) AS inventario_actual, id_proveedor, id_usuario_creacion, id_usuario_modificacion FROM v_Producto GROUP BY id_producto, nombre_producto, descripcion, precio_venta, fecha_vencimiento, venta_libre, precio_descuento, impuesto, fecha_creacion, fecha_modificacion, costo_venta, id_categoria, nombre_categoria, id_departamento, nombre_departamento, id_proveedor, id_usuario_creacion, id_usuario_modificacion";

        return  jdbcTemplate.query(sql, CustomRowMapper.productoProveedorIdRowMapper);
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

    public List<Producto> listarProductosDeSucursalPorCategoria(int idSucursal, int idCategoria) {
        //String sql = "SELECT id_producto, nombre_producto, descripcion, precio_venta, fecha_vencimiento, venta_libre, precio_descuento, impuesto, fecha_creacion, fecha_modificacion, costo_venta, id_categoria, nombre_categoria, id_departamento, nombre_departamento, id_proveedor, id_usuario_creacion, id_usuario_modificacion FROM v_ProductoSucursal WHERE id_sucursal = ? AND id_categoria = ? ";
        String sql = "SELECT id_producto, nombre_producto, descripcion, precio_venta, fecha_vencimiento, venta_libre, precio_descuento, impuesto, fecha_creacion, fecha_modificacion, costo_venta, id_categoria, nombre_categoria, id_departamento, nombre_departamento, SUM(cantidad * factor) AS inventario_actual, id_proveedor, id_usuario_creacion, id_usuario_modificacion FROM v_ProductoSucursal WHERE id_sucursal = ? AND id_categoria = ? GROUP BY id_producto, nombre_producto, descripcion, precio_venta, fecha_vencimiento, venta_libre, precio_descuento, impuesto, fecha_creacion, fecha_modificacion, costo_venta, id_categoria, nombre_categoria, id_departamento, nombre_departamento, id_proveedor, id_usuario_creacion, id_usuario_modificacion";

        return  jdbcTemplate.query(sql, CustomRowMapper.productoDeSucursalRowMapper, idSucursal, idCategoria);
    }

    public List<Producto> filtrarProductosDeSucursalPorNombreSimplificado(String filtro) {
        String sql = "SELECT id_producto, nombre_producto FROM Producto where LOWER(nombre_producto) LIKE ?";

        return  jdbcTemplate.query(sql, CustomRowMapper.productoSucursalSimplificadoRowMapper,
                String.format("%%%s%%", filtro.toLowerCase())
        );
    }

    public List<FichaInventario> listarReportesProductoSucursal(int idProducto, int idSucursal) {
        String sql = "SELECT * FROM v_ReportesProductoSucursal WHERE id_producto = ? AND id_sucursal = ? ORDER BY fecha DESC";

        return jdbcTemplate.query(sql, CustomRowMapper.productoSucursalFichaReportesRowMapper, idProducto, idSucursal);
    }

    public void guardarNuevoProducto(Producto producto) {
        String sql = "INSERT INTO Producto (nombre_producto, descripcion, precio_venta, fecha_vencimiento, venta_libre, precio_descuento, impuesto, fecha_creacion, fecha_modificacion, costo_venta, id_categoria, id_proveedor, id_usuario_creacion,id_usuario_modificacion) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";

        String sqlFicha = "INSERT INTO FichaInventario (cantidad, referencia, fecha, id_producto, id_sucursal, id_tipo_movimiento) SELECT 0, 'Creacion de producto', GETDATE(), p.id_producto, s.id_sucursal, 1 FROM Sucursal s JOIN Producto p ON p.nombre_producto = ? WHERE s.id_sucursal IS NOT NULL ";

        int rows = jdbcTemplate.update(sql, producto.getNombreProducto(), producto.getDescripcion(), producto.getPrecioVenta(), producto.getFechaVencimiento(), producto.getVentaLibre(), producto.getPrecioDescuento(), producto.getImpuesto(), producto.getFechaCreacion(), producto.getFechaModificacion(), producto.getCostoVenta(), producto.getCategoria().getIdCategoria(), producto.getProveedor().getIdProveedor(), producto.getIdUsuarioCreacion(), producto.getIdUsuarioModificacion());
        //System.out.println(rows + " lineas afectadas");

        int rowsFicha = jdbcTemplate.update(sqlFicha, producto.getNombreProducto());
    }

    public void eliminarProductoPorId(int idProducto) {
        String sqlF = "DELETE FROM FichaInventario WHERE id_producto = ?";
        int registrosEliminadosFicha = jdbcTemplate.update(sqlF, idProducto);

        String sql = "DELETE FROM Producto WHERE id_producto = ?";
        int registrosEliminadosProducto = jdbcTemplate.update(sql, idProducto);

    }

    public void actualizarProducto(int idProducto, Producto producto) {
        String sql = "UPDATE Producto SET nombre_producto = ?, descripcion = ?, precio_venta = ?, fecha_vencimiento = ?, venta_libre = ?, precio_descuento = ?, impuesto = ?, fecha_modificacion = GETDATE(), costo_venta = ?, id_categoria = ?, id_proveedor = ?, id_usuario_modificacion = ?  WHERE id_producto = ?";

        jdbcTemplate.update(sql, producto.getNombreProducto(), producto.getDescripcion(), producto.getPrecioVenta(), producto.getFechaVencimiento(), producto.getVentaLibre(), producto.getPrecioDescuento(), producto.getImpuesto(), producto.getCostoVenta(), producto.getCategoria().getIdCategoria(), producto.getProveedor().getIdProveedor(), producto.getIdUsuarioModificacion(), idProducto);
    }

}