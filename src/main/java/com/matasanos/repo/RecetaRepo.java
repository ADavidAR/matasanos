package com.matasanos.repo;


import com.matasanos.model.Receta;
import com.matasanos.model.RecetaProducto;
import com.matasanos.repo.rowmapper.CustomRowMapper;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RecetaRepo {

    JdbcTemplate jdbcTemplate;
    RecetaProductoRepo recetaProductoRepo;


    public RecetaRepo(JdbcTemplate jdbcTemplate, RecetaProductoRepo recetaProductoRepo) {
        this.jdbcTemplate = jdbcTemplate;
        this.recetaProductoRepo = recetaProductoRepo;
    }

    public List<Receta> listarRecetas() {
        String sql = "SELECT id_receta, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, dni, id_cliente, rtn, id_persona, fecha, descripcion, nombre_medico FROM v_RecetaCliente";

        List<Receta> recetas = jdbcTemplate.query(sql, CustomRowMapper.recetaSimpleRowMapper);

        for(Receta r : recetas) {
            r.setProductos(recetaProductoRepo.listarPorId(r.getIdReceta()));
        }
        return recetas;
    }

    public Receta obtenerRecetaPorId(int idReceta) {
        String sqlReceta = "SELECT id_receta, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, dni, id_cliente, rtn, id_persona, fecha, descripcion, nombre_medico FROM v_RecetaCliente WHERE id_receta = ?";

        List<Receta> recetas = jdbcTemplate.query(sqlReceta, CustomRowMapper.recetaSimpleRowMapper, idReceta);
        if(recetas.isEmpty()) return null;

        Receta r = recetas.getFirst();

        List<RecetaProducto> detallesReceta = recetaProductoRepo.listarPorId(idReceta);

        r.setProductos(detallesReceta);

        return r;
    }

    public boolean existe(int idReceta) {
        String sqlReceta = "SELECT 1 FROM Receta WHERE id_receta = ?";

        List<Integer> recetas = jdbcTemplate.queryForList(sqlReceta, Integer.class, idReceta);

        return !recetas.isEmpty();
    }

    public void agregar(Receta receta) {
        String sqk = "INSERT INTO Receta (fecha, descripcion, nombre_medico, id_cliente) VALUES (?, ?, ?, ?)";

        KeyHolder keyHolderReceta = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqk, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setDate(1, Date.valueOf( receta.getFecha()));
            ps.setString(2, receta.getDescripcion());
            ps.setString(3, receta.getNombreMedico());
            ps.setInt(4, receta.getCliente().getIdCliente());
            return ps;
        }, keyHolderReceta);

        int idReceta = keyHolderReceta.getKey().intValue();

        recetaProductoRepo.agregar(receta.getProductos(), idReceta);
    }


    public void modificar(Receta receta) {
        String sqlReceta = "UPDATE Receta SET fecha = ?, descripcion = ?, nombre_medico = ?, id_cliente = ? WHERE id_receta = ?";

        jdbcTemplate.update(
                sqlReceta,
                receta.getFecha(),
                receta.getDescripcion(),
                receta.getNombreMedico(),
                receta.getCliente().getIdCliente(),
                receta.getIdReceta()
        );

        recetaProductoRepo.eliminar(receta.getIdReceta());
        recetaProductoRepo.agregar(receta.getProductos(), receta.getIdReceta());
    }

    public void eliminar(int idReceta) {
        String sqlReceta = "DELETE FROM Receta WHERE  id_receta = ?";

        recetaProductoRepo.eliminar(idReceta);

        jdbcTemplate.update(
                sqlReceta,
                idReceta
        );

    }
}
