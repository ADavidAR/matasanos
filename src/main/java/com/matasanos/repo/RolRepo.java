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
        String sql = "SELECT * FROM Rol";
        return jdbcTemplate.query(sql, CustomRowMapper.rolRowMapper);
    }

    public boolean existeRol(String nombreRol) {
        String sql = "SELECT id_rol FROM Rol WHERE nombre_rol = ?";

        List<Integer> roles = jdbcTemplate.queryForList(sql, Integer.class, nombreRol);

        return !roles.isEmpty();
    }

    public boolean agregarRol(String nombreRol) {

        if(existeRol(nombreRol)) return false;

        String sql = "INSERT INTO Rol (nombre_rol) VALUES (?)";
        int afectado = jdbcTemplate.update(sql, nombreRol);

        return afectado != 0;
    }

    public boolean modificarRol(String nombreRol, int idRol) {
        String sql = "UPDATE Rol SET nombre_rol = ? WHERE id_rol = ?";
        int afectado = jdbcTemplate.update(sql, nombreRol, idRol);

        return afectado != 0;
    }

    public boolean eliminarRol(int idRol) {
        String sqlRolPermiso = "DELETE FROM RolPermiso WHERE id_rol = ?";
        jdbcTemplate.update(sqlRolPermiso,idRol);
        String sqlRol = "DELETE FROM Rol WHERE id_rol = ?";
        int afectado = jdbcTemplate.update(sqlRol, idRol);

        return afectado != 0;
    }
}
