package com.matasanos.repo;

import com.matasanos.model.Rol;
import com.matasanos.repo.rowmapper.CustomRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RolRepo {

    JdbcTemplate jdbcTemplate;

    public RolRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Rol> listarRolesSimples() {
        String sql = "SELECT * FROM v_Rol";
        return jdbcTemplate.query(sql, CustomRowMapper.rolRowMapper);
    }
}
