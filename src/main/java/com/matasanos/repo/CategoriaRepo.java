package com.matasanos.repo;

import com.matasanos.model.Categoria;
import com.matasanos.repo.rowmapper.CustomRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoriaRepo {

    JdbcTemplate jdbcTemplate;

    public CategoriaRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Categoria> listarCategorias(int idDepartamento) {
        String sql = "SELECT c.id_categoria, c.nombre_categoria, d.id_departamento, d.nombre_departamento FROM Categoria c  JOIN Departamento d ON c.id_departamento = d.id_departamento  WHERE c.id_departamento = ?";

        return jdbcTemplate.query(sql, CustomRowMapper.categoriaRowMapper, idDepartamento);
    }

    public List<Categoria> listarCategoriasTodas() {
        String sql = "SELECT c.id_categoria, c.nombre_categoria, d.id_departamento, d.nombre_departamento FROM Categoria c  JOIN Departamento d ON c.id_departamento = d.id_departamento";

        return jdbcTemplate.query(sql, CustomRowMapper.categoriaRowMapper);
    }

}
