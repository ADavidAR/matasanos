package com.matasanos.repo;

import com.matasanos.model.RolPermiso;
import com.matasanos.repo.rowmapper.CustomRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RolPermisoRepo {

    JdbcTemplate jdbcTemplate;

    public RolPermisoRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<RolPermiso> listarPermisosDeRol(int idRol) {
        String sql = "SELECT id_rol_permiso, acceso, modificacion, eliminacion, creacion, id_permiso, descripcion, endpoint_url, acceso_directo FROM v_RolPermiso WHERE id_rol = ?";
        return jdbcTemplate.query(sql, CustomRowMapper.rolPermisoSimpleRowMapper, idRol);
    }
}
