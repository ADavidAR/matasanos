package com.matasanos.repo;


import com.matasanos.model.Caja;
import com.matasanos.repo.rowmapper.CustomRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class CajaRepo {

    JdbcTemplate jdbcTemplate;

    public CajaRepo(JdbcTemplate jdbcTemplate, RolPermisoRepo rolPermisoRepo) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Caja> listarCajas() {
        String sql = "SELECT id_caja, num_caja FROM Caja";

        return jdbcTemplate.query(sql, CustomRowMapper.cajaSinSucursalRowMapper);
    }

    public List<Caja> listarCajasPorSucursal(int idSucursal) {
        String sql = "SELECT id_caja, num_caja FROM Caja WHERE id_sucursal = ?";

        return jdbcTemplate.query(sql, CustomRowMapper.cajaSinSucursalRowMapper, idSucursal);
    }

    public boolean enUso(int idSucursal) {
        String sql = "SELECT 1 AS tabla FROM FacturacionSAR WHERE id_sucursal = ? UNION ALL SELECT 2 AS tabla FROM Caja WHERE id_sucursal = ? UNION ALL SELECT 3 AS tabla FROM FichaInventario WHERE id_sucursal = ? UNION ALL SELECT 4 AS tabla FROM Empleado WHERE id_sucursal = ?";


        return !jdbcTemplate.queryForList(
                sql,
                Integer.class,
                idSucursal,
                idSucursal,
                idSucursal,
                idSucursal
                ).isEmpty();
    }

    public boolean existe(int idCaja) {
        String sql = "SELECT 1 FROM Caja WHERE id_caja = ?";
        return !jdbcTemplate.queryForList(sql, Integer.class, idCaja).isEmpty();
    }

    public void eliminar(int idCaja) {
        String sql = "DELETE FROM Caja WHERE id_caja = ?";
        jdbcTemplate.update(sql, idCaja);
    }

    public void modificar(Caja caja) {
        String sql = "UPDATE Caja SET num_caja = ?, id_sucursal = ? WHERE id_caja = ?";

        jdbcTemplate.update(
                sql,
                caja.getNumCaja(),
                caja.getSucursal().getIdSucursal(),
                caja.getIdCaja()
        );
    }

    public void agregar(Caja caja) {
        String sql = "INSERT INTO Caja (num_caja, id_sucursal) VALUES (?, ?)";

        jdbcTemplate.update(
                sql,
                caja.getNumCaja(),
                caja.getSucursal().getIdSucursal()
        );
    }

    public Caja obtenerCaja(int numCaja, int idSucursal) {
        String sql = "SELECT id_caja, num_caja FROM Caja WHERE num_caja = ? AND id_sucursal = ?";

        List<Caja> cajas = jdbcTemplate.query(sql, CustomRowMapper.cajaSinSucursalRowMapper, numCaja, idSucursal);

        return cajas.isEmpty() ? null : cajas.getFirst();
    }
}
