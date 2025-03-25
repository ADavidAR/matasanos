package com.matasanos.repo;

import com.matasanos.model.Permiso;
import com.matasanos.model.Rol;
import com.matasanos.model.RolPermisos;
import com.matasanos.repo.rowmapper.CustomRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class RolPermisosRepo {

    JdbcTemplate jdbcTemplate;
    PermisoRepo permisoRepo;

    public RolPermisosRepo(JdbcTemplate jdbcTemplate, PermisoRepo permisoRepo) {
        this.jdbcTemplate = jdbcTemplate;
        this.permisoRepo = permisoRepo;
    }

    public List<RolPermisos> listarPermisosPorRol(Rol rol) {

        List<Permiso> permisos = permisoRepo.listarPermisos();
        List<RolPermisos> rolPermisos = new LinkedList<>();

        Map<Integer, RolPermisos> permisosDeRol = new HashMap<>();

        for (RolPermisos rp : rol.getPermisos()) {
            permisosDeRol.put(rp.getPermiso().getIdpermiso(), rp);
        }
        for(Permiso p : permisos) {
            if(permisosDeRol.containsKey(p.getIdpermiso())) {
                rolPermisos.add(permisosDeRol.get(p.getIdpermiso()));
                continue;
            }

            rolPermisos.add(new RolPermisos(p));
        }

        return rolPermisos;
    }

    public List<RolPermisos> listarPermisosDeRol(int idRol) {
        String sql = "SELECT id_rol_permiso, modificacion, eliminacion, creacion, id_permiso, descripcion, pantalla_html, acceso_directo FROM v_RolPermisos WHERE id_rol = ?";
        return jdbcTemplate.query(sql, CustomRowMapper.rolPermisoSimpleRowMapper, idRol);
    }

}
