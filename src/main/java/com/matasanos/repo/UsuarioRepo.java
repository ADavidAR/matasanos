package com.matasanos.repo;


import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.matasanos.model.RolPermiso;
import com.matasanos.model.Usuario;
import com.matasanos.repo.rowmapper.CustomRowMapper;

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

    public List<Usuario> listarUsuariosSimples() {
        String sql = "SELECT id_usuario, usuario FROM Usuario";

        return jdbcTemplate.query(sql, CustomRowMapper.usuarioSimpleSinContrasenaRowMapper);
    }

    public List<String> listarUsuariosConRol(int idRol) {
        String sql = "SELECT usuario FROM Usuario WHERE id_rol = ?";

        return jdbcTemplate.queryForList(sql, String.class, idRol);
    }

    public Usuario obtenerUsuarioEmpleadoRol(String usuario) {
        String sql = "SELECT id_usuario, usuario, usuario_activo, id_rol, nombre_rol, id_empleado, id_persona, segundo_nombre, primer_nombre, primer_apellido, segundo_apellido, id_sucursal, nombre_sucursal FROM v_UsuarioEmpleadoRol WHERE usuario = ?";
        List<Usuario> usuarios = jdbcTemplate.query(sql, CustomRowMapper.usuarioRolEmpleadoRowMapper, usuario);

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

    public Usuario obtenerUsuarioEmpleadoRol(int idUsuario) {
        String sql = "SELECT id_usuario, usuario, usuario_activo, id_rol, nombre_rol, id_empleado, id_persona, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, dni FROM v_UsuarioEmpleadoRol WHERE id_usuario = ?";
        List<Usuario> usuarios = jdbcTemplate.query(sql, CustomRowMapper.usuarioRolEmpleadoSinSucursalRowMapper, idUsuario);

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
        String sql = "SELECT usuario FROM Usuario WHERE id_usuario = ?";
        return !jdbcTemplate.queryForList(sql, String.class, idUsuario).isEmpty();
    }

    public void eliminar(int idUsuario) {
        String sql = "DELETE FROM Usuario WHERE id_usuario = ?";
        jdbcTemplate.update(sql, idUsuario);
    }

    public void modificarUsuario(Usuario usuario) {
        String sql = "UPDATE Usuario SET usuario = ?, id_rol = ?, activo = ?, id_empleado = ?, id_usuario_modificacion = ?, fecha_modificacion = GETDATE() WHERE id_usuario = ?";

        jdbcTemplate.update(sql,
                usuario.getUsuario(),
                usuario.getRol().getIdRol(),
                usuario.getActivo(),
                usuario.getEmpleado().getIdEmpleado() == 0 ? null : usuario.getEmpleado().getIdEmpleado(),
                usuario.getIdUsuarioModificacion(),
                usuario.getIdUsuario()
        );
    }

    public void modificarContrasena(Usuario usuario) {
        String sql = "UPDATE Usuario SET contrasena = ? WHERE id_usuario = ?";

        jdbcTemplate.update(sql,
                usuario.getContrasena(),
                usuario.getIdUsuario()
        );
    }

    public void agregarUsuario(Usuario usuario) {
        String sql = "INSERT INTO Usuario (usuario, contrasena, fecha_creacion, id_rol, activo, id_empleado, id_usuario_creacion) VALUES (?, ?, GETDATE(), ?, ?, ?, ?)";

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
