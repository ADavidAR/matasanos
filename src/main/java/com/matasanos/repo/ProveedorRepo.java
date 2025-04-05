package com.matasanos.repo;

import com.matasanos.model.Proveedor;
import com.matasanos.repo.rowmapper.CustomRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProveedorRepo {

    JdbcTemplate jdbcTemplate;

    public ProveedorRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Proveedor> listarProveedores() {
        String sql = "SELECT * FROM Proveedor";

        return jdbcTemplate.query(sql, CustomRowMapper.proveedorRowMapper);
    }

}
