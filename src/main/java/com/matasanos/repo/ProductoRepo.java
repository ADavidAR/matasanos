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
        String sql = "SELECT * FROM v_ProductoSucursal WHERE id_producto = ? AND id_sucursal = ?";
        List<Producto> productos = jdbcTemplate.query(sql, CustomRowMapper.productoDeSucursalRowMapper, idProducto, idSucursal);

        return (productos.isEmpty()) ? null : productos.getFirst();
    }
}
