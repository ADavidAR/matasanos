package com.matasanos.repo;

import com.matasanos.model.Correo;
import com.matasanos.model.RecetaProducto;
import com.matasanos.repo.rowmapper.CustomRowMapper;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CorreoRepo {

    JdbcTemplate jdbcTemplate;

    public CorreoRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Correo> listarPorId (int idPersona) {
        String sql = "SELECT * FROM Correo WHERE id_persona = ?";
        return jdbcTemplate.query(sql, CustomRowMapper.correoSinPersonaRowMapper, idPersona);
    }
    public void agregar(List<Correo> correos, int idPersona) {
        String sql = "INSERT INTO Correo (correo, id_persona) VALUES (?, ?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Correo c = correos.get(i);

                ps.setString(1, c.getCorreo());
                ps.setInt(2, idPersona);
            }

            @Override
            public int getBatchSize() {
                return correos.size();
            }
        });

    }

    public void eliminar(int idPersona) {
        String sql = "DELETE FROM Correo WHERE id_persona = ?";

        jdbcTemplate.update(sql, idPersona);
    }
}
