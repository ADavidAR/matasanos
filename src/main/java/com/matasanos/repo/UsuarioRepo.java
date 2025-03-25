package com.matasanos.repo;


import com.matasanos.model.RolPermisos;
import com.matasanos.model.Usuario;
import com.matasanos.repo.rowmapper.CustomRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioRepo {

    JdbcTemplate jdbcTemplate;
    RolPermisosRepo rolPermisosRepo;

    public UsuarioRepo(JdbcTemplate jdbcTemplate, RolPermisosRepo rolPermisosRepo) {
        this.jdbcTemplate = jdbcTemplate;
        this.rolPermisosRepo = rolPermisosRepo;
    }

    public List<Usuario> listarUsuarios() {
        String sql = "SELECT * FROM v_UsuarioConRol";

        return jdbcTemplate.query(sql, CustomRowMapper.usuarioRowMapper);
    }

    public Usuario obtenerUsuario(String usuario) {
        String sql = "SELECT * FROM v_Usuario WHERE usuario = ?";
        List<Usuario> usuarios = jdbcTemplate.query(sql, CustomRowMapper.usuarioSimpleRowMapper, usuario);

        return (usuarios.isEmpty()) ? null : usuarios.getFirst();
    }

    public Usuario obtenerUsuarioConRol(String usuario) {
        String sql = "SELECT * FROM v_UsuarioConRol WHERE usuario = ?";
        List<Usuario> usuarios = jdbcTemplate.query(sql, CustomRowMapper.usuarioConRolRowMapper, usuario);

        return (usuarios.isEmpty()) ? null : usuarios.getFirst();
    }


    public Usuario obtenerUsuarioConPermisos(String usuario) {
        Usuario u = obtenerUsuarioConRol(usuario);
        if(u == null)
            return null;

        List<RolPermisos> permisos = rolPermisosRepo.listarPermisosDeRol(u.getRol().getIdRol());

        u.getRol().setPermisos(permisos);

        return u;
    }

}
