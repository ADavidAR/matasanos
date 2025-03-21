package com.matasanos.repo.rowmapper;

import com.matasanos.model.*;
import org.springframework.jdbc.core.RowMapper;

public class CustomRowMapper {
    public static final RowMapper<Permiso> permisoRowMapper = (rs, numCol) ->
            new Permiso( rs.getInt("id_permiso"), rs.getString("descripcion"));

    public static final RowMapper<Usuario> usuarioRowMapper = (rs, numCol) ->
            new Usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("usuario"),
                    rs.getString("contrasena"),
                    rs.getDate("fecha_creacion").toLocalDate(),
                    (rs.getDate("fecha_modificacion") != null) ? rs.getDate("fecha_modificacion").toLocalDate() : null,
                    rs.getInt("id_rol"),
                    rs.getString("nombre_rol"),
                    rs.getInt("id_usuario_creacion"), // retorna 0 si es NULL
                    rs.getInt("id_usuario_modificacion") // retorna 0 si es NULL
            );

    public static final RowMapper<Producto> productoDeSucursalRowMapper = (rs, numCol) ->
            new Producto(
                    rs.getInt("id_producto"),
                    rs.getString("nombre_producto"),
                    rs.getString("descripcion"),
                    rs.getBigDecimal("precio_venta"),
                    rs.getInt("inventario_sucursal"),
                    rs.getDate("fecha_vencimiento").toLocalDate(),
                    rs.getBoolean("venta_libre"),
                    rs.getBigDecimal("precio_descuento"),
                    rs.getDate("fecha_creacion").toLocalDate(),
                    (rs.getDate("fecha_modificacion") != null) ? rs.getDate("fecha_modificacion").toLocalDate() : null,
                    new Categoria( rs.getInt("id_categoria"), rs.getString("nombre_categoria"),rs.getInt("id_categoria") ),
                    rs.getInt("id_usuario_creacion"),
                    rs.getInt("id_usuario_creacion")
            );

    public static final RowMapper<Producto> productoRowMapper = (rs, numCol) ->
            new Producto (
                    rs.getInt("id_producto"),
                    rs.getString("nombre_producto"),
                    rs.getString("descripcion"),
                    rs.getBigDecimal("precio_venta"),
                    rs.getInt("inventario"),
                    rs.getDate("fecha_vencimiento").toLocalDate(),
                    rs.getBoolean("venta_libre"),
                    rs.getBigDecimal("precio_descuento"),
                    rs.getDate("fecha_creacion").toLocalDate(),
                    (rs.getDate("fecha_modificacion") != null) ? rs.getDate("fecha_modificacion").toLocalDate() : null,
                    new Categoria( rs.getInt("id_categoria"), rs.getString("nombre_categoria"),rs.getInt("id_categoria") ),
                    rs.getInt("id_usuario_creacion"),
                    rs.getInt("id_usuario_creacion")
            );
}
