package com.matasanos.repo;
import com.matasanos.repo.rowmapper.CustomRowMapper;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import  com.matasanos.model.*;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.stereotype.Repository;


import java.math.BigDecimal;

import java.time.LocalDate;

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

    public int actualizarPersona(String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String dni,int idPersona){
    String sql = "UPDATE Persona\n " +
            "SET primer_nombre=?,segundo_nombre=?,primer_apellido=?,segundo_apellido=?,dni=?\n " +
            "OUTPUT INSERTED.id_persona WHERE id_persona = ?";
    return jdbcTemplate.queryForObject(sql,int.class,primerNombre,segundoNombre,primerApellido,segundoApellido,dni,idPersona);
    }
    public void actualizarPersonaDireccion( String dni, int idDireccion){
        String sql = "UPDATE Persona\n " +
                "SET id_direccion=?\n " +
                "WHERE dni = ?";
         jdbcTemplate.queryForObject(sql,int.class,idDireccion,dni);
    }

    public void actualizarDireccion(int idDireccion,int idColonia, String referencia) {
        String sql = "UPDATE Direccion\n " +
                "SET id_colonia=?,referencia =?\n " +
                "WHERE id_direccion = ?";
        jdbcTemplate.update(sql, idColonia, referencia,idDireccion);
    }
    public void actualizarEmpleado(BigDecimal salario, LocalDate fechaContratacion, int idCargo, int idUsuarioModificacion,int idSucursal, int idEmpleado){
        String sql = "UPDATE Empleado \n " +
                "SET salario=?,fecha_contratacion=?,fecha_modificacion=GETDATE(),id_cargo=?,id_usuario_modificacion=?,id_sucursal=?\n " +
                "WHERE id_empleado = ?";
         jdbcTemplate.update(sql,salario,fechaContratacion,idCargo,idUsuarioModificacion,idSucursal,idEmpleado);
    }
    public void actualizarTelefono(int idPersona, String telefono){
        String sql = "UPDATE Telefono\n " +
                "SET telefono =?\n " +
                "WHERE id_persona = ?";
        jdbcTemplate.update(sql,telefono,idPersona);
    }
    public void actualizarCorreo(int idPersona, String correo){
        String sql = "UPDATE Correo\n " +
                "SET correo=?\n " +
                "WHERE id_persona = ?";

        jdbcTemplate.update(sql,correo,idPersona);
    }
    public void BorrarEmpleado( int idEmpleado ){

        String sql1 = "UPDATE Usuario " +
                "SET id_empleado = NULL " +
                "WHERE id_empleado=?";

        String sql2 = "DELETE Empleado \n " +
                "WHERE id_empleado = ?";

        jdbcTemplate.update(sql1,idEmpleado);
        jdbcTemplate.update(sql2,idEmpleado);
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
        String sql = "select id_Persona from Persona\n " +
                "WHERE dni = ?";
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, dni);
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }
    public int obtenerIdPersona(int idEmpleado){
        String sql = "select P.id_Persona from Empleado E\n " +
                "INNER JOIN Persona P ON P.id_persona=E.id_persona " +
                "WHERE E.id_empleado = ?";
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, idEmpleado);
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }
    public int obtenerIdDireccion(String dni){
        String sql = "select id_direccion from Persona " +
                "WHERE dni = ?";
        Integer i=jdbcTemplate.queryForObject(sql, Integer.class, dni);
       if (i==null)
            return 0;
       else{return i;}

    }


    public List<Empleado> listarEmpleados() {
        String sql = "SELECT * FROM Empleado E \n " +
                "INNER JOIN Persona P ON p.id_persona=E.id_persona \n " +
                "INNER JOIN Sucursal S ON s.id_sucursal= E.id_sucursal \n " +
                "INNER JOIN Cargo C ON C.id_cargo= E.id_cargo\n" +
                "INNER JOIN Direccion D ON D.id_direccion=P.id_direccion\n " +
                "INNER JOIN Colonia Co ON Co.id_colonia=D.id_colonia\n " +
                "INNER JOIN Ciudad Ci ON Ci.id_ciudad = Co.id_ciudad ";
        return jdbcTemplate.query(sql,empleadoRowMapper);
    }

    public boolean tieneTelefono(int idPersona){
        String sql = "SELECT CASE WHEN EXISTS(SELECT 1 FROM Telefono WHERE id_persona = ?) THEN 1 ELSE 0 END";
        return jdbcTemplate.queryForObject(sql,int.class,idPersona)==1;
    }
    public boolean tieneCorreo(int idPersona){
        String sql = "SELECT CASE WHEN EXISTS(SELECT 1 FROM Correo WHERE id_persona = ?) THEN 1 ELSE 0 END";
        return jdbcTemplate.queryForObject(sql,int.class,idPersona)==1;
    }

    public Empleado obtenerEmpleado(int idEmpleado){
        String sql = "SELECT * FROM Empleado E \n " +
                "INNER JOIN Persona P ON p.id_persona=E.id_persona \n " +
                "INNER JOIN Sucursal S ON s.id_sucursal= E.id_sucursal \n " +
                "INNER JOIN Cargo C ON C.id_cargo= E.id_cargo\n" +
                "INNER JOIN Direccion D ON D.id_direccion=P.id_direccion\n " +
                "INNER JOIN Colonia Co ON Co.id_colonia=D.id_colonia\n " +
                "INNER JOIN Ciudad Ci ON Ci.id_ciudad = Co.id_ciudad WHERE E.id_empleado = ?";
        return jdbcTemplate.queryForObject(sql,empleadoRowMapper,idEmpleado);
    }
    public Persona obtenerPersona(String dni){
        String sql = "SELECT * FROM Persona P\n " +
                "LEFT JOIN Direccion D ON D.id_direccion=P.id_direccion\n " +
                "LEFT JOIN Colonia Co ON Co.id_colonia=D.id_colonia\n " +
                "LEFT JOIN Ciudad Ci ON Ci.id_ciudad = Co.id_ciudad WHERE P.dni = ?";
        try {
        return jdbcTemplate.queryForObject(sql,CustomRowMapper.personaRowMapper,dni);
        } catch (Exception e) {
            return  jdbcTemplate.queryForObject(sql,personaSinDireccionRowMapper,dni);
        }
    }
    public Telefono obtenerTelefono(int idPersona){
        String sql ="SELECT * FROM Telefono WHERE id_persona=? ";
        try {
            return jdbcTemplate.queryForObject(sql, telefonoRowMapper, idPersona);
        } catch (Exception e) {
           return  new Telefono();
        }

    }
    public Correo obtenerCorreo(int idPersona){
        String sql ="SELECT * FROM Correo WHERE id_persona=? ";
        try {
            return jdbcTemplate.queryForObject(sql, correoRowMapper, idPersona);
        } catch (Exception e) {
            return  new Correo();
        }
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
    public static final RowMapper<Correo> correoRowMapper = (rs, numCol) ->
            new Correo(
                    rs.getInt("id_correo"),
                    rs.getString("correo"),
                    null
            );
    public static final RowMapper<Telefono> telefonoRowMapper = (rs, numCol) ->
            new Telefono(
                    rs.getInt("id_telefono"),
                    rs.getString("telefono"),
                    null
            );

    public static final RowMapper<Persona> personaSinDireccionRowMapper = (rs, numCol) ->
            new Persona(
                    rs.getInt("id_persona"),
                    rs.getString("primer_nombre"),
                    rs.getString("segundo_nombre"),
                    rs.getString("primer_apellido"),
                    rs.getString("segundo_apellido"),
                    rs.getString("dni"),
                    new Direccion()
            );

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

        return jdbcTemplate.query(sql, CustomRowMapper.empleadoSimpleRowMapper);
    }
}
