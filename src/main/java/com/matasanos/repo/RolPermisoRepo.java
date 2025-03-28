package com.matasanos.repo;

import com.matasanos.model.RolPermisos;
import com.matasanos.repo.rowmapper.CustomRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class RolPermisoRepo {

    JdbcTemplate jdbcTemplate;
    PermisoRepo permisoRepo;

    public RolPermisoRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.permisoRepo = permisoRepo;
    }

    public List<RolPermisos> listarPermisosPorRol(int idRol) {

        List<Permiso> permisos = permisoRepo.listarPermisos();
        List<RolPermisos> rolPermisos = new LinkedList<>();

        Map<Integer, RolPermisos> permisosDeRol = new HashMap<>();


        for (RolPermisos rp : listarPermisosDeRol(idRol)) {
            permisosDeRol.put(rp.getPermiso().getIdPermiso(), rp);
        }
        for(Permiso p : permisos) {
            if(permisosDeRol.containsKey(p.getIdPermiso())) {
                rolPermisos.add(permisosDeRol.get(p.getIdPermiso()));
                continue;
            }

            rolPermisos.add(new RolPermisos(p));
        }

        return rolPermisos;
    }

    public List<RolPermiso> listarPermisosDeRol(int idRol) {
        String sql = "SELECT id_rol_permiso, acceso, modificacion, eliminacion, creacion, id_permiso, descripcion, endpoint_url, acceso_directo FROM v_RolPermiso WHERE id_rol = ?";
        return jdbcTemplate.query(sql, CustomRowMapper.rolPermisoSimpleRowMapper, idRol);
    }

    public boolean actualizarPermisosDeRol(List<RolPermisos> rps) {
        for(var rp : rps) {
            int afectados;

            if(rp.getIdRolPermiso() != 0) {
                String sql = "UPDATE RolPermisos SET acceso = ?, modificacion = ?, eliminacion = ?, creacion = ?, id_permiso = ?, id_rol = ? WHERE id_rol_permiso = ?";

                afectados = jdbcTemplate.update(
                        sql,
                        rp.getAcceso() ? 1 : 0,
                        rp.getModificacion() ? 1 : 0,
                        rp.getEliminacion() ? 1 : 0,
                        rp.getCreacion() ? 1 : 0,
                        rp.getPermiso().getIdPermiso(),
                        rp.getRol().getIdRol(),
                        rp.getIdRolPermiso()
                );

                if (afectados == 0) {
                    return false;
                }
                continue;
            }

                String sql = "INSERT INTO RolPermisos (acceso, modificacion, eliminacion, creacion, id_permiso, id_rol) VALUES (?, ?, ?, ?, ?, ?)";

                afectados = jdbcTemplate.update(
                        sql,
                        rp.getAcceso() ? 1 : 0,
                        rp.getModificacion() ? 1 : 0,
                        rp.getEliminacion() ? 1 : 0,
                        rp.getCreacion() ? 1 : 0,
                        rp.getPermiso().getIdPermiso(),
                        rp.getRol().getIdRol()
                );

                if (afectados == 0) {
                    return false;
                }
            }

        return true;
    }

}

