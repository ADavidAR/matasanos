package com.matasanos.repo;

import com.matasanos.repo.rowmapper.CustomRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import  com.matasanos.model.*;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public class CompraRepo {
    JdbcTemplate jdbcTemplate;
    public CompraRepo(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }



    public Compra compra(int idCompra) {
        String sql = "select * from Compra C " +
                "INNER JOIN Proveedor P ON P.id_proveedor = C.id_proveedor WHERE C. id_compra=?";
        return jdbcTemplate.queryForObject(sql, CustomRowMapper.compraRowMapper,idCompra);
    }

        public List<Compra> listarCompras(){
        String sql= "select * from Compra C " +
                "INNER JOIN Proveedor P ON P.id_proveedor = C.id_proveedor ORDER BY  id_compra DESC";
        return jdbcTemplate.query(sql, CustomRowMapper.compraRowMapper);
        }
        public  List<Proveedor> listarProveedores() {
        String sql= "select * from Proveedor";
        return jdbcTemplate.query(sql, CustomRowMapper.proveedorRowMapper);
    }
        public List<Producto> listarProductoProveedor(int idProveedor){
        String sql="SELECT *  from Producto where id_proveedor = ?";
        return jdbcTemplate.query(sql,productoRowMapper,idProveedor);
        }
        public  int  crearCompra(LocalDate fechaCompra,BigDecimal costoTotal, int idProveedor,  String numFactura){
        String sql= "insert into Compra(fecha_compra,costo_total,id_proveedor,num_factura_compra) output inserted.id_compra values (?,?,?,?)";
        return  jdbcTemplate.queryForObject(sql,int.class,fechaCompra,costoTotal,idProveedor,numFactura);

        }
        public  void creaProdcutoCompra(int cantidad,BigDecimal costo,int idCompra,int idProducto){
        String sql = "insert into ProductoCompra(cantidad,costo,id_compra,id_producto) values (?,?,?,?)";
        jdbcTemplate.update(sql,cantidad,costo,idCompra,idProducto);

        }

        public  List<ProductoCompra> productosCompra(int idCompra){
        String sql = "SELECT PC.*,P.* FROM ProductoCompra PC INNER JOIN Producto P ON P.id_producto=PC.id_producto WHERE id_compra = ?";
        return  jdbcTemplate.query(sql,productoCompraRowMapper,idCompra);
        }


        public void crearFichaCompra(int cantidad,int idCompra, int idProducto,int idSucursal){
        String idCompr= Integer.toString(idCompra);
        String sql ="INSERT INTO FichaInventario(cantidad,referencia,fecha,id_producto,id_sucursal,id_tipo_movimiento) VALUES (?,?,GETDATE(),?,?,1)";
        jdbcTemplate.update(sql,cantidad,idCompr,idProducto,idSucursal);
        }
        public  void actualizarCompra(String numFactura){
        String sql = "UPDATE Compra SET num_factura_compra=?,fecha_entrega=GETDATE()";
        jdbcTemplate.update(sql,numFactura);
        }
    public static final RowMapper<Producto> productoRowMapper = (rs, numCol) ->
            new Producto(
                    rs.getInt("id_producto"),
                    rs.getString("nombre_producto"),
                    null,
                    null,
                    null,
                    false,
                    null,
                    null,
                    null,
                    null,
                    null,
                    -1,
                    null,
                    null,
                    0,
                    0
            );
    public static final RowMapper<ProductoCompra> productoCompraRowMapper = (rs, numCol) ->
            new ProductoCompra(
                    rs.getInt("id_producto_compra"),
                    rs.getInt("cantidad"),
                    rs.getBigDecimal("costo"),
                    null,
                    productoRowMapper.mapRow(rs,numCol)

            );

}



