package com.matasanos.repo;


import com.matasanos.model.Permiso;
import com.matasanos.model.Usuario;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioRepo {

    JdbcTemplate jdbcTemplate;

    public UsuarioRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Usuario> listarUsuarios() {
        String sql = "SELECT * FROM v_UsuarioConRol";

        return jdbcTemplate.query(sql, usuarioRowMapper);
    }

    public Usuario obtenerUsuario(String usuario) {
        String sql = "SELECT * FROM v_UsuarioConRol WHERE usuario = ?";
        return jdbcTemplate.queryForObject(sql, usuarioRowMapper, usuario);
    }

    public Usuario obtenerUsuarioConPermisos (String usuario) {
        Usuario u = obtenerUsuario(usuario);
        if(u == null)
            return null;

        String sql = "SELECT id_permiso, descripcion FROM v_UsuarioConPermiso WHERE id_usuario = ?";
        List<Permiso> permisos = jdbcTemplate.query(sql, permisoRowMapper, u.getIdusuario());

        u.setPermisos(permisos);

        return u;
    }

    private final RowMapper<Usuario> usuarioRowMapper = (rs, numCol) ->
        new Usuario(
                rs.getInt("id_usuario"),
                rs.getString("usuario"),
                rs.getString("contrasena"),
                rs.getDate("fecha_creacion").toLocalDate(),
                (rs.getDate("fecha_modificacion") != null) ? rs.getDate("fecha_modificacion").toLocalDate() : null,
                rs.getInt("id_rol"),
                rs.getString("nombre_rol"),
                rs.getInt("id_usuario_creacion"), // retorna 0 si es NULL
                rs.getInt("id_usuario_modificacion") // retorna 0 si es NULL
        );

    private final RowMapper<Permiso> permisoRowMapper = (rs, numCol) ->
        new Permiso( rs.getInt("id_permiso"), rs.getString("descripcion"));

}
