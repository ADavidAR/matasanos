package com.matasanos.repo;

import com.matasanos.model.RolPermisos;
import com.matasanos.repo.rowmapper.CustomRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RolPermisosRepo {

    JdbcTemplate jdbcTemplate;

    public RolPermisosRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<RolPermisos> listarPermisosDeRol(int idRol) {
        String sql = "SELECT id_rol_permiso, acceso, modificacion, eliminacion, creacion, id_permiso, descripcion, endpoint_url, acceso_directo FROM v_RolPermisos WHERE id_rol = ?";
        return jdbcTemplate.query(sql, CustomRowMapper.rolPermisoSimpleRowMapper, idRol);
    }
}
