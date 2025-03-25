package com.matasanos.repo;

import com.matasanos.model.Permiso;
import com.matasanos.repo.rowmapper.CustomRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PermisoRepo {

    JdbcTemplate jdbcTemplate;

    public PermisoRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Permiso> listarPermisos() {
        String sql = "SELECT * FROM v_Permiso";
        return jdbcTemplate.query(sql, CustomRowMapper.permisoRowMapper);
    }

}
