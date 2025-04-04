package com.matasanos.repo;


import com.matasanos.model.RolPermiso;
import com.matasanos.model.Usuario;
import com.matasanos.repo.rowmapper.CustomRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioRepo {

    JdbcTemplate jdbcTemplate;
    RolPermisoRepo rolPermisoRepo;

    public UsuarioRepo(JdbcTemplate jdbcTemplate, RolPermisoRepo rolPermisoRepo) {
        this.jdbcTemplate = jdbcTemplate;
        this.rolPermisoRepo = rolPermisoRepo;
    }

    public List<Usuario> listarUsuarios() {
        String sql = "SELECT * FROM v_UsuarioConRol";

        return jdbcTemplate.query(sql, CustomRowMapper.usuarioConRolRowMapper);
    }

    public Usuario obtenerUsuario(String usuario) {
        String sql = "SELECT id_usuario, usuario, contrasena FROM Usuario WHERE usuario = ?";
        List<Usuario> usuarios = jdbcTemplate.query(sql, CustomRowMapper.usuarioSimpleRowMapper, usuario);

        return (usuarios.isEmpty()) ? null : usuarios.getFirst();
    }

    public Usuario obtenerUsuarioEmpleadoRol(String usuario) {
        String sql = "SELECT id_usuario, usuario, usuario_activo, id_rol, nombre_rol, id_empleado, id_persona, segundo_nombre, primer_nombre, primer_apellido, segundo_apellido, id_sucursal, nombre_sucursal FROM v_UsuarioEmpleadoRol WHERE usuario = ?";
        List<Usuario> usuarios = jdbcTemplate.query(sql, CustomRowMapper.usuarioEmpleadoRolRowMapper, usuario);

        return (usuarios.isEmpty()) ? null : usuarios.getFirst();
    }


    public Usuario obtenerUsuarioConPermisos(String usuario) {
        Usuario u = obtenerUsuarioEmpleadoRol(usuario);
        if(u == null)
            return null;

        List<RolPermiso> permisos = rolPermisoRepo.listarPermisosDeRol(u.getRol().getIdRol());

        u.getRol().setPermisos(permisos);

        return u;
    }

}
