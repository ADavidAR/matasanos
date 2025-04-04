package com.matasanos.repo;

import com.matasanos.model.Empleado;
import com.matasanos.repo.rowmapper.CustomRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmpleadoRepo {

    JdbcTemplate jdbcTemplate;

    public EmpleadoRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public boolean tieneUsuarioAsignado(int idEmpleado) {
        String sql = "SELECT id_usuario FROM v_UsuarioEmpleadoRol WHERE id_empleado = ?";
        List<Integer> usuarios = jdbcTemplate.queryForList(sql, Integer.class, idEmpleado);

        if(usuarios.isEmpty())
            return false;
        return !(usuarios.getFirst() == null);
    }

    public boolean tieneUsuarioAsignado(int idEmpleado, int idUsuario) {
        String sql = "SELECT id_usuario FROM v_UsuarioEmpleadoRol WHERE id_empleado = ?";
        List<Integer> usuarios = jdbcTemplate.queryForList(sql, Integer.class, idEmpleado);

        if(usuarios.isEmpty())
            return false;
        return !(usuarios.getFirst() == null) && usuarios.getFirst() != idUsuario;
    }

    public List<Empleado> listarEmpleadosSinUsuario() {
        String sql = "SELECT id_empleado, id_persona, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, dni FROM v_UsuarioEmpleadoRol WHERE id_usuario IS NULL AND id_empleado IS NOT NULL";

        return jdbcTemplate.query(sql, CustomRowMapper.empleadoSimpleowMapper);
    }
}
