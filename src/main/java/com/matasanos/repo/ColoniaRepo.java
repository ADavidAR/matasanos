package com.matasanos.repo;

import com.matasanos.model.Colonia;
import com.matasanos.repo.rowmapper.CustomRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ColoniaRepo {

    JdbcTemplate jdbcTemplate;

    public ColoniaRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Colonia> listarColoniasPorCiudad(int idCiudad) {
        String sql = "SELECT id_colonia, nombre_colonia FROM Colonia WHERE id_ciudad = ?";
        return jdbcTemplate.query(sql, CustomRowMapper.coloniaSinCiudadRowMapper, idCiudad);
    }
}
