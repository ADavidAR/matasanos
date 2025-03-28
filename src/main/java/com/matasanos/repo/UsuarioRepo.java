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

        return jdbcTemplate.query(sql, CustomRowMapper.usuarioRowMapper);
    }

    public Usuario obtenerUsuario(String usuario) {
        String sql = "SELECT id_usuario, usuario, contrasena FROM v_Usuario WHERE usuario = ?";
        List<Usuario> usuarios = jdbcTemplate.query(sql, CustomRowMapper.usuarioSimpleRowMapper, usuario);

        return (usuarios.isEmpty()) ? null : usuarios.getFirst();
    }

    public Usuario obtenerUsuarioConRol(String usuario) {
        String sql = "SELECT id_usuario, usuario, contrasena, id_rol, nombre_rol FROM v_UsuarioConRol WHERE usuario = ?";
        List<Usuario> usuarios = jdbcTemplate.query(sql, CustomRowMapper.usuarioConRolRowMapper, usuario);

        return (usuarios.isEmpty()) ? null : usuarios.getFirst();
    }


    public Usuario obtenerUsuarioConPermisos(String usuario) {
        Usuario u = obtenerUsuarioConRol(usuario);
        if(u == null)
            return null;

        List<RolPermiso> permisos = rolPermisoRepo.listarPermisosDeRol(u.getRol().getIdRol());

        u.getRol().setPermisos(permisos);

        return u;
    }

}
