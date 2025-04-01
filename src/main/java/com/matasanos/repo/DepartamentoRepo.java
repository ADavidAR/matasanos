package com.matasanos.repo;

import com.matasanos.model.Departamento;
import com.matasanos.repo.rowmapper.CustomRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartamentoRepo {

    JdbcTemplate jdbcTemplate;

    public DepartamentoRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Departamento> listarDepartamentos() {
        String sql = "SELECT * FROM Departamento";

        return jdbcTemplate.query(sql, CustomRowMapper.departamentoRowMapper);
    }

}
