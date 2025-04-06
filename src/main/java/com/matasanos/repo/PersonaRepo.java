package com.matasanos.repo;


import com.matasanos.model.Cliente;
import com.matasanos.model.Persona;
import com.matasanos.repo.rowmapper.CustomRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class PersonaRepo {

    JdbcTemplate jdbcTemplate;

    public PersonaRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void modificar(Persona persona) {
        String sqlPersona = "UPDATE Persona SET primer_nombre = ?, segundo_nombre = ?, primer_apellido = ?, segundo_apellido = ?, dni = ? WHERE id_persona = ?";

        jdbcTemplate.update(
                sqlPersona,
                persona.getPrimerNombre(),
                persona.getSegundoNombre(),
                persona.getPrimerApellido(),
                persona.getSegundoApellido(),
                persona.getDni(),
                persona.getIdPersona()
        );

        String sqlDireccion = "UPDATE  Direccion SET referencia = ?, id_colonia = ? WHERE id_direccion = ?";

        jdbcTemplate.update(
                sqlPersona,
                persona.getDireccion().getReferencia(),
                persona.getDireccion().getColonia().getIdColonia(),
                persona.getDireccion().getIdDireccion()
        );

    }

    public int agregar(Persona persona) {
        String sqlDireccion = "INSERT INTO Direccion (referencia, id_colonia) VALUES (?, ?)";

        KeyHolder keyHolderDireccion = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlDireccion, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, persona.getDireccion().getReferencia());
            ps.setInt(2, persona.getDireccion().getColonia().getIdColonia());
            return ps;
        }, keyHolderDireccion);

        int idDireccion = (int) keyHolderDireccion.getKey().longValue();

        String sql = "INSERT INTO Persona (primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, dni, id_direccion) VALUES (?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolderPersona = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, persona.getPrimerNombre());
            ps.setString(2, persona.getSegundoNombre());
            ps.setString(3, persona.getPrimerApellido());
            ps.setString(4, persona.getSegundoApellido());
            ps.setString(5, persona.getDni());
            ps.setInt(6 , idDireccion);
            return ps;
        }, keyHolderPersona);

        return  keyHolderPersona.getKey().intValue();
    }

    public Persona obtenerPersonaPorDni(String dni) {
        String sql = "SELECT id_persona ,primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, dni FROM Persona WHERE dni = ?";

        List<Persona> personas = jdbcTemplate.query(sql, CustomRowMapper.personaSinDireccionRowMapper, dni);

        return personas.isEmpty() ? null : personas.getFirst();
    }

    public Persona obtenerPersonaPorId(int idPersona) {
        String sql = "SELECT id_persona ,primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, dni FROM Persona WHERE id_persona = ?";

        List<Persona> personas = jdbcTemplate.query(sql, CustomRowMapper.personaSinDireccionRowMapper, idPersona);

        return personas.isEmpty() ? null : personas.getFirst();
    }

}
