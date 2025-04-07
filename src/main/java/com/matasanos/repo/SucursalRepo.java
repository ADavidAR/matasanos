package com.matasanos.repo;


import com.matasanos.model.Sucursal;
import com.matasanos.repo.rowmapper.CustomRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class SucursalRepo {

    JdbcTemplate jdbcTemplate;

    public SucursalRepo(JdbcTemplate jdbcTemplate, RolPermisoRepo rolPermisoRepo) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Sucursal> listarSucursales() {
        String sql = "SELECT * FROM v_SucursalDireccion";

        return jdbcTemplate.query(sql, CustomRowMapper.sucursalRowMapper);
    }

    public List<Sucursal> listarSucursalesSimples() {
        String sql = "SELECT id_sucursal, nombre_sucursal FROM Sucursal";

        return jdbcTemplate.query(sql, CustomRowMapper.sucursalSinDireccionRowMapper);
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

    public boolean existe(int idSucursal) {
        String sql = "SELECT nombre_sucursal FROM Sucursal WHERE id_sucursal = ?";
        return !jdbcTemplate.queryForList(sql, String.class, idSucursal).isEmpty();
    }

    public void eliminar(int idSucursal) {
        String sql = "DELETE FROM Sucursal WHERE id_sucursal = ?";
        jdbcTemplate.update(sql, idSucursal);
    }

    public void modificar(Sucursal sucursal) {
        String sqlDireccion = "UPDATE Direccion SET referencia = ?, id_colonia = ? WHERE id_direccion = ?";

        jdbcTemplate.update(sqlDireccion,
                sucursal.getDireccion().getReferencia(),
                sucursal.getDireccion().getColonia().getIdColonia(),
                sucursal.getDireccion().getIdDireccion()
        );

        String sqlSucursal = "UPDATE Sucursal SET nombre_sucursal = ? WHERE id_sucursal = ?";



        jdbcTemplate.update(sqlSucursal,
                sucursal.getNombreSucursal(),
                sucursal.getIdSucursal()
        );
    }

    public void agregar(Sucursal sucursal) {
        String sqlDireccion = "INSERT INTO Direccion (referencia, id_colonia) VALUES (?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlDireccion, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, sucursal.getDireccion().getReferencia());
            ps.setInt(2, sucursal.getDireccion().getColonia().getIdColonia());
            return ps;
        }, keyHolder);

        int idDireccion = (int) keyHolder.getKey().longValue();
        String sqlSucursal = "INSERT INTO Sucursal (nombre_sucursal, id_direccion) VALUES (?, ?)";

        jdbcTemplate.update(sqlSucursal,
                sucursal.getNombreSucursal(),
                idDireccion
        );
    }

    public Sucursal obtenerSucursal(String nombreSucursal) {
        String sql = "SELECT nombre_sucursal, id_sucursal FROM Sucursal WHERE nombre_sucursal = ?";

        List<Sucursal> sucursal = jdbcTemplate.query(sql, CustomRowMapper.sucursalSinDireccionRowMapper,nombreSucursal);

        return sucursal.isEmpty() ? null : sucursal.getFirst();
    }

    public Sucursal obtenerSucursalDireccion(int idSucursal) {
        String sql = "SELECT * FROM v_SucursalDireccion WHERE id_sucursal = ?";

        List<Sucursal> sucursal = jdbcTemplate.query(sql, CustomRowMapper.sucursalRowMapper,idSucursal);

        return sucursal.isEmpty() ? null : sucursal.getFirst();
    }
}
