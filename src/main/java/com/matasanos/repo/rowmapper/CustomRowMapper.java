package com.matasanos.repo.rowmapper;

import com.matasanos.model.*;
import org.springframework.jdbc.core.RowMapper;

public class CustomRowMapper {
    public static final RowMapper<Permiso> permisoRowMapper = (rs, numCol) ->
            new Permiso(
                    rs.getInt("id_permiso"),
                    rs.getString("descripcion"),
                    rs.getBoolean("acceso_directo"),
                    rs.getString("pantalla_html")
            );

    public static final RowMapper<Rol> rolRowMapper = (rs, numCol) ->
            new Rol(
                    rs.getInt("id_rol"),
                    rs.getString("nombre_rol")
            );
    public static final RowMapper<RolPermisos> rolPermisoRowMapper = (rs, numCol) ->
            new RolPermisos(
                    rs.getInt("id_rol_permiso"),
                    CustomRowMapper.rolRowMapper.mapRow(rs, numCol),
                    CustomRowMapper.permisoRowMapper.mapRow(rs, numCol),
                    rs.getBoolean("acceso"),
                    rs.getBoolean("modificacion"),
                    rs.getBoolean("eliminacion"),
                    rs.getBoolean("creacion")
            );

    public static final RowMapper<RolPermisos> rolPermisoSimpleRowMapper = (rs, numCol) ->
            new RolPermisos(
                    rs.getInt("id_rol_permiso"),
                    null,
                    CustomRowMapper.permisoRowMapper.mapRow(rs, numCol),
                    rs.getBoolean("acceso"),
                    rs.getBoolean("modificacion"),
                    rs.getBoolean("eliminacion"),
                    rs.getBoolean("creacion")
            );
    public static final RowMapper<Usuario> usuarioRowMapper = (rs, numCol) ->
            new Usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("usuario"),
                    rs.getString("contrasena"),
                    rs.getDate("fecha_creacion").toLocalDate(),
                    (rs.getDate("fecha_modificacion") != null) ? rs.getDate("fecha_modificacion").toLocalDate() : null,
                    CustomRowMapper.rolRowMapper.mapRow(rs, numCol),
                    rs.getInt("id_usuario_creacion"), // retorna 0 si es NULL
                    rs.getInt("id_usuario_modificacion") // retorna 0 si es NULL
            );

    public static final RowMapper<Usuario> usuarioConRolRowMapper = (rs, numCol) ->
            new Usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("usuario"),
                    rs.getString("contrasena"),
                    CustomRowMapper.rolRowMapper.mapRow(rs, numCol)
            );

    public static final RowMapper<Usuario> usuarioSimpleRowMapper = (rs, numCol) ->
            new Usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("usuario"),
                    rs.getString("contrasena")
            );

    public static final RowMapper<Departamento> departamentoRowMapper = (rs, numCol) ->
            new Departamento(
                    rs.getInt("id_departamento"),
                    rs.getString("nombre_departamento")
            );

    public static final RowMapper<Categoria> categoriaRowMapper = (rs, numCol) ->
            new Categoria(
                    rs.getInt("id_categoria"),
                    rs.getString("nombre_categoria"),
                    CustomRowMapper.departamentoRowMapper.mapRow(rs, numCol)
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
                    CustomRowMapper.categoriaRowMapper.mapRow(rs, numCol),
                    rs.getInt("id_usuario_creacion"), // retorna 0 si es NULL
                    rs.getInt("id_usuario_creacion") // retorna 0 si es NULL
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
                    CustomRowMapper.categoriaRowMapper.mapRow(rs, numCol),
                    rs.getInt("id_usuario_creacion"), // retorna 0 si es NULL
                    rs.getInt("id_usuario_creacion") // retorna 0 si es NULL
            );
}
