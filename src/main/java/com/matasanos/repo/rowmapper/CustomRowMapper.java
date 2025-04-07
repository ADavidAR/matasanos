package com.matasanos.repo.rowmapper;


import com.matasanos.model.*;
import org.springframework.jdbc.core.RowMapper;
import java.time.LocalDate;

public class CustomRowMapper {

    public static final RowMapper<Ciudad> ciudadRowMapper = (rs, numCol) ->
            new Ciudad(
                    rs.getInt("id_ciudad"),
                    rs.getString("ciudad")
            );

    public static final RowMapper<Colonia> coloniaRowMapper = (rs, numCol) ->
            new Colonia(
                    rs.getInt("id_colonia"),
                    rs.getString("nombre_colonia"),
                    CustomRowMapper.ciudadRowMapper.mapRow(rs, numCol)
            );

    public static final RowMapper<Colonia> coloniaSinCiudadRowMapper = (rs, numCol) ->
            new Colonia(
                    rs.getInt("id_colonia"),
                    rs.getString("nombre_colonia"),
                    null
            );

    public static final RowMapper<Direccion> direccionRowMapper = (rs, numCol) ->
            new Direccion(
                    rs.getInt("id_direccion"),
                    rs.getString("referencia"),
                    CustomRowMapper.coloniaRowMapper.mapRow(rs, numCol)
            );

    public static final RowMapper<Sucursal> sucursalRowMapper = (rs, numCol) ->
            new Sucursal(
                    rs.getInt("id_sucursal"),
                    CustomRowMapper.direccionRowMapper.mapRow(rs, numCol),
                    rs.getString("nombre_sucursal")
            );

    public static final RowMapper<Sucursal> sucursalSinDireccionRowMapper = (rs, numCol) ->
            new Sucursal(
                    rs.getInt("id_sucursal"),
                    null,
                    rs.getString("nombre_sucursal")
            );

    public static final RowMapper<TipoMovimiento> tipoMovimientoRowMapper = (rs, numCol) ->
            new TipoMovimiento(
                    rs.getInt("id_tipo_movimiento"),
                    rs.getString("nombre"),
                    rs.getInt("factor")
            );

    public static final RowMapper<Funcion> funcionRowMapper = (rs, numCol) ->
            new Funcion(
                    rs.getInt("id_funcion"),
                    rs.getString("descripcion")
            );

    public static final RowMapper<Permiso> permisoRowMapper = (rs, numCol) ->
            new Permiso(
                    rs.getInt("id_permiso"),
                    rs.getString("descripcion"),
                    rs.getBoolean("acceso_directo"),
                    rs.getString("endpoint_url")
            );

    public static final RowMapper<Departamento> departamentoRowMapper = (rs, numCol) ->
            new Departamento(
                    rs.getInt("id_departamento"),
                    rs.getString("nombre_departamento")
            );

    public static final RowMapper<Proveedor> proveedorRowMapper = (rs, numCol) ->
            new Proveedor(
                    rs.getInt("id_proveedor"),
                    rs.getString("razon_social"),
                    rs.getString("contacto"),
                    rs.getString("RTN_contacto"),
                    rs.getString("telefono"),
                    rs.getString("correo"),
                    rs.getString("direccion")
            );

    public static RowMapper<Proveedor> proveedorIdRowMapper = (rs, numCol) ->
            new Proveedor(
                    rs.getInt("id_proveedor"),
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
            );

    public static final RowMapper<FacturacionSAR> facturacionSarRowMapper = (rs, numCol) ->
            new FacturacionSAR(
                    rs.getInt("id_factura_sar"),
                    CustomRowMapper.sucursalRowMapper.mapRow(rs, numCol),
                    rs.getObject("fecha_vigencia", LocalDate.class),
                    rs.getInt("rango_inicio"),
                    rs.getInt("rango_fin"),
                    rs.getBoolean("vigente"),
                    rs.getString("cai"),
                    rs.getString("inicio_cod_factura")
            );

    public static final RowMapper<Descuento> descuentoRowMapper = (rs, numCol) ->
            new Descuento(
                    rs.getInt("id_descuento"),
                    rs.getString("descuento_nombre"),
                    rs.getBigDecimal("porcentaje")
            );

    public static final RowMapper<Rol> rolRowMapper = (rs, numCol) ->
            new Rol(
                    rs.getInt("id_rol"),
                    rs.getString("nombre_rol")
            );

    public static final RowMapper<Cargo> cargoRowMapper = (rs, numCol) ->
            new Cargo(
                    rs.getInt("id_cargo"),
                    rs.getString("descripcion")
            );

    public static final RowMapper<FuncionCargo> funcionCargoRowMapper = (rs, numCol) ->
            new FuncionCargo(
                    rs.getInt("id_funcion_cargo"),
                    CustomRowMapper.funcionRowMapper.mapRow(rs, numCol),
                    CustomRowMapper.cargoRowMapper.mapRow(rs, numCol)
            );

    public static final RowMapper<RolPermiso> rolPermisoRowMapper = (rs, numCol) ->
            new RolPermiso(
                    rs.getInt("id_rol_permiso"),
                    CustomRowMapper.rolRowMapper.mapRow(rs, numCol),
                    CustomRowMapper.permisoRowMapper.mapRow(rs, numCol),
                    rs.getBoolean("acceso"),
                    rs.getBoolean("modificacion"),
                    rs.getBoolean("eliminacion"),
                    rs.getBoolean("creacion")
            );

    public static final RowMapper<RolPermiso> rolPermisoSimpleRowMapper = (rs, numCol) ->
            new RolPermiso(
                    rs.getInt("id_rol_permiso"),
                    null,
                    CustomRowMapper.permisoRowMapper.mapRow(rs, numCol),
                    rs.getBoolean("acceso"),
                    rs.getBoolean("modificacion"),
                    rs.getBoolean("eliminacion"),
                    rs.getBoolean("creacion")
            );

    public static final RowMapper<Categoria> categoriaRowMapper = (rs, numCol) ->
            new Categoria(
                    rs.getInt("id_categoria"),
                    rs.getString("nombre_categoria"),
                    CustomRowMapper.departamentoRowMapper.mapRow(rs, numCol)
            );

    public static final RowMapper<Usuario> usuarioRowMapper = (rs, numCol) ->
            new Usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("usuario"),
                    rs.getString("contrasena"),
                    rs.getBoolean("activo"),
                    rs.getObject("fecha_creacion", LocalDate.class),
                    rs.getObject("fecha_modificacion", LocalDate.class),
                    CustomRowMapper.rolRowMapper.mapRow(rs, numCol),
                    CustomRowMapper.empleadoRowMapper.mapRow(rs, numCol),
                    rs.getInt("id_usuario_creacion"),
                    rs.getInt("id_usuario_modificacion")
            );

    public static final RowMapper<Usuario> usuarioConRolRowMapper = (rs, numCol) ->
            new Usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("usuario"),
                    rs.getString("contrasena"),
                    rs.getBoolean("activo"),
                    null,
                    null,
                    CustomRowMapper.rolRowMapper.mapRow(rs, numCol),
                    null,
                    0,
                    0
            );

    public static final RowMapper<Usuario> usuarioEmpleadoRolRowMapper = (rs, numCol) ->
            new Usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("usuario"),
                    null,
                    rs.getBoolean("usuario_activo"),
                    null,
                    null,
                    CustomRowMapper.rolRowMapper.mapRow(rs, numCol),
                    CustomRowMapper.empleadoSimpleConSucursalRowMapper.mapRow(rs, numCol),
                    0,
                    0
            );

    public static final RowMapper<Usuario> usuarioSimpleRowMapper = (rs, numCol) ->
            new Usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("usuario"),
                    rs.getString("contrasena"),
                    false,
                    null,
                    null,
                    null,
                    null,
                    0,
                    0
            );

    public static final RowMapper<Persona> personaRowMapper = (rs, numCol) ->
            new Persona(
                    rs.getInt("id_persona"),
                    rs.getString("primer_nombre"),
                    rs.getString("segundo_nombre"),
                    rs.getString("primer_apellido"),
                    rs.getString("segundo_apellido"),
                    rs.getString("dni"),
                    CustomRowMapper.direccionRowMapper.mapRow(rs, numCol)
            );

    public static final RowMapper<Persona> personaSinDireccionRowMapper = (rs, numCol) ->
            new Persona(
                    rs.getInt("id_persona"),
                    rs.getString("primer_nombre"),
                    rs.getString("segundo_nombre"),
                    rs.getString("primer_apellido"),
                    rs.getString("segundo_apellido"),
                    rs.getString("dni"),
                    null
            );

    public static final RowMapper<Persona> personaSinDireccionDNIRowMapper = (rs, numCol) ->
            new Persona(
                    rs.getInt("id_persona"),
                    rs.getString("primer_nombre"),
                    rs.getString("segundo_nombre"),
                    rs.getString("primer_apellido"),
                    rs.getString("segundo_apellido"),
                    null,
                    null
            );

    public static final RowMapper<Cliente> clienteRowMapper = (rs, numCol) ->
            new Cliente(
                    rs.getInt("id_cliente"),
                    rs.getString("rtn"),
                    rs.getObject("fecha_creacion", LocalDate.class),
                    rs.getObject("fecha_modificacion", LocalDate.class),
                    CustomRowMapper.personaRowMapper.mapRow(rs, numCol),
                    rs.getInt("id_usuario_creacion"),
                    rs.getInt("id_usuario_modificacion")
            );

    public static final RowMapper<Cliente> clienteSinDireccionRowMapper = (rs, numCol) ->
            new Cliente(
                    rs.getInt("id_cliente"),
                    rs.getString("rtn"),
                    rs.getObject("fecha_creacion", LocalDate.class),
                    null,
                    CustomRowMapper.personaSinDireccionRowMapper.mapRow(rs, numCol),
                    0,
                    0
            );

    public static final RowMapper<Cliente> clienteParaRecetaRowMapper = (rs, numCol) ->
            new Cliente(
                    rs.getInt("id_cliente"),
                    null,
                    null,
                    null,
                    CustomRowMapper.personaSinDireccionRowMapper.mapRow(rs, numCol),
                    0,
                    0
            );

    public static final RowMapper<Cliente> clienteConDireccionRowMapper = (rs, numCol) ->
            new Cliente(
                    rs.getInt("id_cliente"),
                    rs.getString("rtn"),
                    rs.getObject("fecha_creacion", LocalDate.class),
                    null,
                    CustomRowMapper.personaRowMapper.mapRow(rs, numCol),
                    0,
                    0
            );

    public static final RowMapper<Caja> cajaRowMapper = (rs, numCol) ->
            new Caja(
                    rs.getInt("id_caja"),
                    rs.getInt("num_caja"),
                    CustomRowMapper.sucursalRowMapper.mapRow(rs, numCol)
            );

    public static final RowMapper<Factura> facturaRowMapper = (rs, numCol) ->
            new Factura(
                    rs.getInt("id_factura"),
                    rs.getString("numero_factura"),
                    rs.getObject("fecha_emision", LocalDate.class),
                    rs.getString("rtn_cliente"),
                    rs.getBigDecimal("subtotal"),
                    rs.getBigDecimal("impuesto"),
                    CustomRowMapper.facturacionSarRowMapper.mapRow(rs, numCol),
                    CustomRowMapper.usuarioRowMapper.mapRow(rs, numCol),
                    CustomRowMapper.clienteRowMapper.mapRow(rs, numCol),
                    CustomRowMapper.cajaRowMapper.mapRow(rs, numCol)
            );

    public static final RowMapper<FacturaDescuento> facturaDescuentoRowMapper = (rs, numCol) ->
            new FacturaDescuento(
                    rs.getInt("id_factura_descuento"),
                    CustomRowMapper.facturaRowMapper.mapRow(rs, numCol),
                    CustomRowMapper.descuentoRowMapper.mapRow(rs, numCol)
            );

    public static final RowMapper<Producto> productoRowMapper = (rs, numCol) ->
            new Producto(
                    rs.getInt("id_producto"),
                    rs.getString("nombre_producto"),
                    rs.getString("descripcion"),
                    rs.getBigDecimal("precio_venta"),
                    rs.getObject("fecha_vencimiento", LocalDate.class),
                    rs.getBoolean("venta_libre"),
                    rs.getBigDecimal("precio_descuento"),
                    rs.getBigDecimal("impuesto"),
                    rs.getObject("fecha_creacion", LocalDate.class),
                    rs.getObject("fecha_modificacion", LocalDate.class),
                    rs.getBigDecimal("costo_venta"),
                    rs.getInt("inventario_actual"),
                    CustomRowMapper.categoriaRowMapper.mapRow(rs, numCol),
                    CustomRowMapper.proveedorRowMapper.mapRow(rs, numCol),
                    rs.getInt("id_usuario_creacion"),
                    rs.getInt("id_usuario_modificacion")
            );

    public static final RowMapper<Producto> productoDeRecetaRowMapper = (rs, numCol) ->
            new Producto(
                    rs.getInt("id_producto"),
                    rs.getString("nombre_producto"),
                    rs.getString("descripcion"),
                    null,
                    null,
                    false,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    0,
                    0
            );


    public static final RowMapper<Producto> productoProveedorIdRowMapper = (rs, numCol) ->
            new Producto(
                    rs.getInt("id_producto"),
                    rs.getString("nombre_producto"),
                    rs.getString("descripcion"),
                    rs.getBigDecimal("precio_venta"),
                    rs.getObject("fecha_vencimiento", LocalDate.class),
                    rs.getBoolean("venta_libre"),
                    rs.getBigDecimal("precio_descuento"),
                    rs.getBigDecimal("impuesto"),
                    rs.getObject("fecha_creacion", LocalDate.class),
                    rs.getObject("fecha_modificacion", LocalDate.class),
                    rs.getBigDecimal("costo_venta"),
                    rs.getInt("inventario_actual"),
                    CustomRowMapper.categoriaRowMapper.mapRow(rs, numCol),
                    proveedorIdRowMapper.mapRow(rs, numCol),
                    rs.getInt("id_usuario_creacion"),
                    rs.getInt("id_usuario_modificacion")
            );

    public static final RowMapper<Producto> productoDeSucursalRowMapper = (rs, numCol) ->
            new Producto(
                    rs.getInt("id_producto"),
                    rs.getString("nombre_producto"),
                    rs.getString("descripcion"),
                    rs.getBigDecimal("precio_venta"),
                    rs.getObject("fecha_vencimiento", LocalDate.class),
                    rs.getBoolean("venta_libre"),
                    rs.getBigDecimal("precio_descuento"),
                    rs.getBigDecimal("impuesto"),
                    rs.getObject("fecha_creacion", LocalDate.class),
                    rs.getObject("fecha_modificacion", LocalDate.class),
                    rs.getBigDecimal("costo_venta"),
                    rs.getInt("inventario_actual"),
                    CustomRowMapper.categoriaRowMapper.mapRow(rs, numCol),
                    proveedorIdRowMapper.mapRow(rs, numCol),
                    rs.getInt("id_usuario_creacion"), // retorna 0 si es NULL
                    rs.getInt("id_usuario_modificacion") // retorna 0 si es NULL
            );

    public static final RowMapper<FichaInventario> fichaInventarioRowMapper = (rs, numCol) ->
            new FichaInventario(
                    rs.getInt("id_ficha"),
                    rs.getInt("cantidad"),
                    rs.getString("referencia"),
                    rs.getObject("fecha", LocalDate.class),
                    CustomRowMapper.productoRowMapper.mapRow(rs, numCol),
                    CustomRowMapper.sucursalRowMapper.mapRow(rs, numCol),
                    CustomRowMapper.tipoMovimientoRowMapper.mapRow(rs, numCol)
            );

    public static final RowMapper<FacturaProducto> facturaProductoRowMapper = (rs, numCol) ->
            new FacturaProducto(
                    rs.getInt("id_factura_producto"),
                    rs.getInt("cantidad"),
                    rs.getBigDecimal("precio_unitario"),
                    rs.getBigDecimal("impuset_porcentaje"),
                    rs.getBigDecimal("impuesto"),
                    rs.getBigDecimal("subtotal"),
                    CustomRowMapper.facturaRowMapper.mapRow(rs, numCol),
                    CustomRowMapper.productoRowMapper.mapRow(rs, numCol)
            );

    public static final RowMapper<Receta> recetaRowMapper = (rs, numCol) ->
            new Receta(
                    rs.getInt("id_receta"),
                    rs.getObject("fecha", LocalDate.class),
                    rs.getString("descripcion"),
                    rs.getString("nombre_medico"),
                    CustomRowMapper.clienteRowMapper.mapRow(rs, numCol)
            );

    public static final RowMapper<Receta> recetaSimpleRowMapper = (rs, numCol) ->
            new Receta(
                    rs.getInt("id_receta"),
                    rs.getObject("fecha", LocalDate.class),
                    rs.getString("descripcion"),
                    rs.getString("nombre_medico"),
                    CustomRowMapper.clienteParaRecetaRowMapper.mapRow(rs, numCol)
            );

    public static final RowMapper<RecetaProducto> recetaProductoRowMapper = (rs, numCol) ->
            new RecetaProducto(
                    rs.getInt("id_receta_producto"),
                    rs.getInt("cantidad"),
                    rs.getString("indicaciones"),
                    CustomRowMapper.recetaRowMapper.mapRow(rs, numCol),
                    CustomRowMapper.productoRowMapper.mapRow(rs, numCol)
            );

    public static final RowMapper<RecetaProducto> recetaProductoSimpleRowMapper = (rs, numCol) ->
            new RecetaProducto(
                    rs.getInt("id_receta_producto"),
                    rs.getInt("cantidad"),
                    rs.getString("indicaciones"),
                    null,
                    CustomRowMapper.productoDeRecetaRowMapper.mapRow(rs, numCol)
            );

    public static final RowMapper<Compra> compraRowMapper = (rs, numCol) ->
            new Compra(
                    rs.getInt("id_compra"),
                    rs.getString("num_factura_compra"),
                    rs.getObject("fecha_compra", LocalDate.class),
                    rs.getObject("fecha_entrega", LocalDate.class),
                    rs.getBigDecimal("costo_total"),
                    CustomRowMapper.proveedorRowMapper.mapRow(rs, numCol)
            );

    public static final RowMapper<ProductoCompra> productoCompraRowMapper = (rs, numCol) ->
            new ProductoCompra(
                    rs.getInt("id_producto_compra"),
                    rs.getInt("cantidad"),
                    rs.getBigDecimal("costo"),
                    CustomRowMapper.compraRowMapper.mapRow(rs, numCol),
                    CustomRowMapper.productoRowMapper.mapRow(rs, numCol)
            );

    public static final RowMapper<Empleado> empleadoRowMapper = (rs, numCol) ->
            new Empleado(
                    rs.getInt("id_empleado"),
                    rs.getBigDecimal("salario"),
                    rs.getObject("fecha_contratacion", LocalDate.class),
                    rs.getObject("fecha_baja", LocalDate.class),
                    rs.getBoolean("activo"),
                    rs.getObject("fecha_creacion", LocalDate.class),
                    rs.getObject("fecha_modificacion", LocalDate.class),
                    CustomRowMapper.personaRowMapper.mapRow(rs, numCol),
                    CustomRowMapper.cargoRowMapper.mapRow(rs, numCol),
                    CustomRowMapper.sucursalRowMapper.mapRow(rs, numCol),
                    rs.getInt("id_usuario_creacion"),
                    rs.getInt("id_usuario_modificacion")
            );

    public static final RowMapper<Empleado> empleadoSimpleConSucursalRowMapper = (rs, numCol) ->
            new Empleado(
                    rs.getInt("id_empleado"),
                    null,
                    null,
                    null,
                    false,
                    null,
                    null,
                    CustomRowMapper.personaSinDireccionDNIRowMapper.mapRow(rs, numCol),
                    null,
                    CustomRowMapper.sucursalSinDireccionRowMapper.mapRow(rs, numCol),
                    0,
                    0
            );

    public static final RowMapper<MetodoPago> metodoPagoRowMapper = (rs, numCol) ->
            new MetodoPago(
                    rs.getInt("id_metodo"),
                    rs.getString("descripcion")
            );

    public static final RowMapper<Pago> pagoRowMapper = (rs, numCol) ->
            new Pago(
                    rs.getInt("id_pago"),
                    rs.getBigDecimal("importe"),
                    rs.getObject("fecha", LocalDate.class),
                    CustomRowMapper.facturaRowMapper.mapRow(rs, numCol),
                    CustomRowMapper.metodoPagoRowMapper.mapRow(rs, numCol)
            );

    public static final RowMapper<Correo> correoRowMapper = (rs, numCol) ->
            new Correo(
                    rs.getInt("id_correo"),
                    rs.getString("correo"),
                    CustomRowMapper.personaRowMapper.mapRow(rs, numCol)
            );

    public static final RowMapper<Telefono> telefonoRowMapper = (rs, numCol) ->
            new Telefono(
                    rs.getInt("id_telefono"),
                    rs.getString("telefono"),
                    CustomRowMapper.personaRowMapper.mapRow(rs, numCol)
            );
}