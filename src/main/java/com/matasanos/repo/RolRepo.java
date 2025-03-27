package com.matasanos.repo;

import com.matasanos.model.Rol;
import com.matasanos.model.Usuario;
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

    public boolean existeRol(String nombreRol) {
        String sql = "SELECT id_rol FROM v_Rol WHERE nombre_rol = ?";

        List<Integer> roles = jdbcTemplate.queryForList(sql, Integer.class, nombreRol);

        return !roles.isEmpty();
    }

    public boolean agregarRol(String nombreRol) {

        if(existeRol(nombreRol)) return false;

        String sql = "INSERT INTO v_Rol (nombre_rol) VALUES (?)";
        int afectado = jdbcTemplate.update(sql, nombreRol);

        return afectado != 0;
    }

    public boolean modificarRol(String nombreRol, int idRol) {
        String sql = "UPDATE v_Rol SET nombre_rol = ? WHERE id_rol = ?";
        int afectado = jdbcTemplate.update(sql, nombreRol, idRol);

        return afectado != 0;
    }

    public boolean eliminarRol(int idRol) {
        String sql = "DELETE FROM v_Rol WHERE id_rol = ?";
        int afectado = jdbcTemplate.update(sql, idRol);

        return afectado != 0;
    }
}
