package com.matasanos.repo;
import com.matasanos.repo.rowmapper.CustomRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import  com.matasanos.model.*;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Repository
public class EmpleadoRepo {
    JdbcTemplate jdbcTemplate;

    public EmpleadoRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Cargo> listarCargos() {
        String sql = "SELECT *  FROM Cargo";
        return jdbcTemplate.query(sql, CustomRowMapper.cargoRowMapper);
    }

    public List<Sucursal> listarSucursal() {
        String sql = "SELECT *  FROM Sucursal";
        return jdbcTemplate.query(sql, sucursalRowMapper);
    }

    public List<Ciudad> listarCiudad() {
        String sql = "SELECT *  FROM Ciudad";
        return jdbcTemplate.query(sql, CustomRowMapper.ciudadRowMapper);
    }

    public List<Colonia> listarColonias(int idCiudad) {
        String sql = "SELECT *  FROM Colonia WHERE id_ciudad = ?";
        return jdbcTemplate.query(sql, coloniaRowMapper,idCiudad);
    }

    public int crearDireccion(String referencia, int idColonia) {
        String sql = "INSERT INTO Direccion(referencia,id_colonia) OUTPUT INSERTED.id_direccion VALUES(?,?)";
        return jdbcTemplate.queryForObject(sql, int.class, referencia, idColonia);
    }

    public int crearPersona(String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String dni, int idDireccion) {
        String sql = "INSERT INTO Persona(primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,dni,id_direccion) OUTPUT INSERTED.id_persona VALUES(?,?,?,?,?,?)";
        return jdbcTemplate.queryForObject(sql, int.class, primerNombre, segundoNombre, primerApellido, segundoApellido, dni, idDireccion);

    }

    public void crearTelefono(int idPersona, String telefono) {
        String sql = "INSERT INTO Telefono(id_persona,telefono) VALUES (?,?)";
        jdbcTemplate.update(sql, idPersona, telefono);
    }

    public void crearCorreo(int idPersona, String correo) {
        String sql = "INSERT INTO Correo(id_persona,correo) VALUES (?,?)";
        jdbcTemplate.update(sql, idPersona, correo);
    }

    public void crearEmpleado(BigDecimal salario, LocalDate fecha_contratacion,int idSucursal, int idPersona, int idCargo, int idUsuarioCreacion) {
        String sql = "INSERT INTO Empleado(salario,fecha_contratacion,activo,fecha_creacion,id_sucursal,id_persona,id_cargo,id_usuario_creacion) VALUES (?,?,1,GETDATE(),?,?,?,?)";
        jdbcTemplate.update(sql, salario, fecha_contratacion,idSucursal, idPersona, idCargo, idUsuarioCreacion);
    }

    public int actualizarPersona(String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String dni, int idDireccion){
    String sql = "UPDATE Persona\n" +
            "SET primer_nombre=?,segundo_nombre=?,primer_apellido=?,segundo_apellido=?,dni=?,id_direccion=?\n" +
            "OUTPUT INSERTED.id_persona WHERE DNI = ?";
    return jdbcTemplate.queryForObject(sql,int.class,primerNombre,segundoNombre,primerApellido,segundoApellido,dni,idDireccion);
    }
    public int actualizarEmpleado(BigDecimal salario, Date fecha_modificacion, int idPersona, int idCargo, int idUsuarioModificacion){
        String sql = "UPDATE Empleado \n" +
                "SET salario=?,fecha_contratacion=?,fecha_modificacion=?,id_cargo=?,id_usuario_modificacion=?\n" +
                "WHERE id_persona = ?";
        return jdbcTemplate.queryForObject(sql,int.class,salario,fecha_modificacion,idCargo,idUsuarioModificacion,idPersona);
    }
    public void actualizarTelefono(int idPersona, String telefono){
        String sql = "UPDATE Telefono\n" +
                "SET telefono =?\n" +
                "WHERE id_persona = ?";
        jdbcTemplate.update(sql,telefono,idPersona);
    }
    public void actualizarCorreo(int idPersona, String correo){
        String sql = "UPDATE Correo\n" +
                "SET correo=?\n" +
                "WHERE id_persona = ?";

        jdbcTemplate.update(sql,correo,idPersona);
    }
    public void BorrarEmpleado( String dni){
        int idEMpleado= obtenerIdEmpleado(dni);
        String sql1 = "UPDATE Usuario" +
                "SET id_empleado = NULL" +
                "WHERE id_empleado=?";

        String sql2 = "DELETE Empleado \n" +
                "WHERE dni = ?";

        jdbcTemplate.update(sql1,idEMpleado);
        jdbcTemplate.update(sql2,dni);
    }


    public int obtenerIdEmpleado(String dni){
        String sql = " select E.id_empleado from Empleado E " +
                "INNER JOIN Persona P ON P.id_persona = E.id_persona " +
                "WHERE P.dni = ?";
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, dni);
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }
    public int obtenerIdPersona(String dni){
        String sql = " select id_Persona from Persona  " +
                "WHERE dni = ?";
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, dni);
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }


    public List<Empleado> listarEmpleados() {
        String sql = "SELECT * FROM Empleado E \n" +
                "INNER JOIN Persona P ON p.id_persona=E.id_persona \n" +
                "INNER JOIN Sucursal S ON s.id_sucursal= E.id_sucursal \n" +
                "INNER JOIN Cargo C ON C.id_cargo= E.id_cargo\n" +
                "INNER JOIN Direccion D ON D.id_direccion=P.id_direccion\n" +
                "INNER JOIN Colonia Co ON Co.id_colonia=D.id_colonia\n" +
                "INNER JOIN Ciudad Ci ON Ci.id_ciudad = Co.id_ciudad";
        return jdbcTemplate.query(sql,empleadoRowMapper);
    }
    public Empleado optenerEMpleado(String dni){
        String sql = "SELECT * FROM Empleado E \n" +
                "INNER JOIN Persona P ON p.id_persona=E.id_persona \n" +
                "INNER JOIN Sucursal S ON s.id_sucursal= E.id_sucursal \n" +
                "INNER JOIN Cargo C ON C.id_cargo= E.id_cargo\n" +
                "INNER JOIN Direccion D ON D.id_direccion=P.id_direccion\n" +
                "INNER JOIN Colonia Co ON Co.id_colonia=D.id_colonia\n" +
                "INNER JOIN Ciudad Ci ON Ci.id_ciudad = Co.id_ciudad WHERE E.dni = ?";
        return jdbcTemplate.queryForObject(sql,empleadoRowMapper,dni);
    }
    public Persona optenerPersona(String dni){
        String sql = "SELECT * FROM Persona P \n" +
                "INNER JOIN Direccion D ON D.id_direccion=P.id_direccion\n" +
                "INNER JOIN Colonia Co ON Co.id_colonia=D.id_colonia\n" +
                "INNER JOIN Ciudad Ci ON Ci.id_ciudad = Co.id_ciudad WHERE E.dni = ?";
        return jdbcTemplate.queryForObject(sql,CustomRowMapper.personaRowMapper,dni);
    }




    public static final RowMapper<Colonia> coloniaRowMapper = (rs, numCol) ->
            new Colonia(
                    rs.getInt("id_colonia"),
                    rs.getString("nombre_colonia"),
                    null
            );
    public static final RowMapper<Sucursal> sucursalRowMapper = (rs, numCol) ->
            new Sucursal(
                    rs.getInt("id_sucursal"),
                    null,
                    rs.getString("nombre_sucursal")
            );

    public static final RowMapper<Empleado> empleadoRowMapper = (rs, numCol) ->
            new Empleado(
                    rs.getInt("id_empleado"),
                    rs.getBigDecimal("salario"),
                    rs.getObject("fecha_contratacion", LocalDate.class),
                    rs.getObject("fecha_baja", LocalDate.class),
                    rs.getBoolean("activo"),
                    null,
                    null,
                    CustomRowMapper.personaRowMapper.mapRow(rs, numCol),
                    CustomRowMapper.cargoRowMapper.mapRow(rs, numCol),
                    sucursalRowMapper.mapRow(rs,numCol),
                    0,
                    0
            );



}



