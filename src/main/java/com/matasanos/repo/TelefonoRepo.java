package com.matasanos.repo;

import com.matasanos.model.Correo;
import com.matasanos.model.Telefono;
import com.matasanos.repo.rowmapper.CustomRowMapper;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TelefonoRepo {

    JdbcTemplate jdbcTemplate;

    public TelefonoRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Telefono> listarPorId (int idPersona) {
        String sql = "SELECT * FROM Telefono WHERE id_persona = ?";
        return jdbcTemplate.query(sql, CustomRowMapper.telefonoSinPersonaRowMapper, idPersona);
    }
    public void agregar(List<Telefono> telefono, int idPersona) {
        String sql = "INSERT INTO Telefono (telefono, id_persona) VALUES (?, ?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Telefono t = telefono.get(i);

                ps.setString(1, t.getTelefono());
                ps.setInt(2, idPersona);
            }

            @Override
            public int getBatchSize() {
                return telefono.size();
            }
        });

    }

    public void eliminar(int idPersona) {
        String sql = "DELETE FROM Telefono WHERE id_persona = ?";

        jdbcTemplate.update(sql, idPersona);
    }
}
