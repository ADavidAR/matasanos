package com.matasanos.repo;

import com.matasanos.model.Ciudad;
import com.matasanos.model.Empleado;
import com.matasanos.repo.rowmapper.CustomRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CiudadRepo {

    JdbcTemplate jdbcTemplate;

    public CiudadRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Ciudad> listarCiudades() {
        String sql = "SELECT * FROM Ciudad";
        return jdbcTemplate.query(sql, CustomRowMapper.ciudadRowMapper);
    }
}
