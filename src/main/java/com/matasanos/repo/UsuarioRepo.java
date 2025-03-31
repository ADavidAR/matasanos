package com.matasanos.repo;


import com.matasanos.model.RolPermiso;
import com.matasanos.model.Usuario;
import com.matasanos.repo.rowmapper.CustomRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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

    public List<Usuario> listarUsuariosSimples() {
        String sql = "SELECT id_usuario, usuario FROM v_Usuario";

        return jdbcTemplate.query(sql, CustomRowMapper.usuarioSimpleSinContrasenaRowMapper);
    }

    public List<String> listarUsuariosConRol(int idRol) {
        String sql = "SELECT usuario FROM v_Usuario WHERE id_rol = ?";

        return jdbcTemplate.queryForList(sql, String.class, idRol);
    }

    public Usuario obtenerUsuario(String usuario) {
        String sql = "SELECT id_usuario, usuario, contrasena FROM v_Usuario WHERE usuario = ?";
        List<Usuario> usuarios = jdbcTemplate.query(sql, CustomRowMapper.usuarioSimpleRowMapper, usuario);

        return (usuarios.isEmpty()) ? null : usuarios.getFirst();
    }

    public Usuario obtenerUsuarioConRol(String usuario) {
        String sql = "SELECT id_usuario, usuario, contrasena, activo,  id_rol, nombre_rol FROM v_UsuarioConRol WHERE usuario = ?";
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

    public Usuario obtenerUsuarioEmpleadoRol(int idUsuario) {
        String sql = "SELECT id_usuario, usuario, usuario_activo, id_rol, nombre_rol, id_empleado, id_persona, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, dni FROM v_UsuarioEmpleadoRol WHERE id_usuario = ?";
        List<Usuario> usuarios = jdbcTemplate.query(sql, CustomRowMapper.usuarioRolEmpleadoRowMapper, idUsuario);

        if(usuarios.isEmpty())
            return false;
        return usuarios.getFirst();
    }

    public boolean haRealizadoModificaciones(int idUsuario) {
        String sql = "SELECT 'Producto' AS tabla FROM v_Producto WHERE id_usuario_modificacion = ? OR id_usuario_creacion = ? UNION ALL SELECT 'Empleado' AS tabla FROM Empleado WHERE id_usuario_modificacion = ? OR id_usuario_creacion = ? UNION ALL SELECT 'Cliente' AS tabla FROM Cliente WHERE id_usuario_modificacion = ? OR id_usuario_creacion = ? UNION ALL SELECT 'Usuario' AS tabla FROM Usuario WHERE id_usuario_modificacion = ? OR id_usuario_creacion = ?";


        return !jdbcTemplate.queryForList(
                sql,
                String.class,
                idUsuario,
                idUsuario,
                idUsuario,
                idUsuario,
                idUsuario,
                idUsuario,
                idUsuario,
                idUsuario
                ).isEmpty();
    }

    public boolean existeUsuario(int idUsuario) {
        String sql = "SELECT usuario FROM v_Usuario WHERE id_usuario = ?";
        return jdbcTemplate.queryForList(sql, String.class, idUsuario).isEmpty();
    }

    public void eliminar(int idUsuario) {
        String sql = "DELETE FROM v_Usuario id_usuario = ?";
        jdbcTemplate.update(sql, idUsuario);
    }

    public void modificarUsuario(Usuario usuario) {
        String sql = "UPDATE v_Usuario SET usuario = ?, id_rol = ?, contrasena = ?, activo = ?, id_empleado = ?, id_usuario_modificacion = ?, fecha_modificacion = GETDATE()";

        jdbcTemplate.update(sql,
                usuario.getUsuario(),
                usuario.getRol().getIdRol(),
                usuario.getContrasena(),
                usuario.getActivo(),
                usuario.getEmpleado().getIdEmpleado() == 0 ? null : usuario.getEmpleado().getIdEmpleado(),
                usuario.getIdUsuarioModificacion()
        );
    }

    public void modificarUsuarioConContrasena(Usuario usuario) {
        String sql = "UPDATE v_Usuario SET usuario = ?, id_rol = ?, activo = ?, id_empleado = ?, id_usuario_modificacion = ?, fecha_modificacion = GETDATE()";

        jdbcTemplate.update(sql,
                usuario.getUsuario(),
                usuario.getRol().getIdRol(),
                usuario.getActivo(),
                usuario.getEmpleado().getIdEmpleado() == 0 ? null : usuario.getEmpleado().getIdEmpleado(),
                usuario.getIdUsuarioModificacion()
        );
    }

    public void agregarUsuario(Usuario usuario) {
        String sql = "INSERT INTO v_Usuario (usuario, contrasena, fecha_creacion, id_rol, activo, id_empleado, id_usuario_creacion) VALUES (?, ?, GETDATE(), ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                usuario.getUsuario(),
                usuario.getContrasena(),
                usuario.getRol().getIdRol(),
                usuario.getActivo(),
                usuario.getEmpleado().getIdEmpleado() == 0 ? null : usuario.getEmpleado().getIdEmpleado(),
                usuario.getIdUsuarioCreacion()
        );
    }
}
