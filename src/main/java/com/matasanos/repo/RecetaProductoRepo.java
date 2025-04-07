package com.matasanos.repo;

import com.matasanos.model.RecetaProducto;
import com.matasanos.repo.rowmapper.CustomRowMapper;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RecetaProductoRepo {

    JdbcTemplate jdbcTemplate;

    public RecetaProductoRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<RecetaProducto> listarPorId (int idReceta) {
        String sql = "SELECT * FROM v_RecetaProducto WHERE id_receta = ?";
        return jdbcTemplate.query(sql, CustomRowMapper.recetaProductoSimpleRowMapper, idReceta);
    }
    public void agregar(List<RecetaProducto> detallesReceta, int idRecete) {
        String sqlDetalles = "INSERT INTO RecetaProducto (cantidad, indicaciones, id_receta, id_producto) VALUES (?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sqlDetalles, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                RecetaProducto rp = detallesReceta.get(i);

                ps.setInt(1, rp.getCantidad());
                ps.setString(2, rp.getIndicaciones());
                ps.setLong(3, idRecete);
                ps.setLong(4, rp.getProducto().getIdProducto());
            }

            @Override
            public int getBatchSize() {
                return detallesReceta.size();
            }
        });

    }

    public void eliminar(int idReceta) {
        String sql = "DELETE FROM RecetaProducto WHERE id_receta = ?";

        jdbcTemplate.update(sql, idReceta);
    }


}
