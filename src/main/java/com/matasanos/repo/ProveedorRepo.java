package com.matasanos.repo;

import com.matasanos.model.Proveedor;
import com.matasanos.repo.rowmapper.CustomRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProveedorRepo {

    JdbcTemplate jdbcTemplate;

    public ProveedorRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Proveedor> listarProveedores() {
        String sql = "SELECT * FROM Proveedor";

        return jdbcTemplate.query(sql, CustomRowMapper.proveedorRowMapper);
    }

    public Proveedor obtenerProveedorPorId(int idProveedor) {
        String sql = "SELECT * FROM Proveedor WHERE id_proveedor = ?";
        List<Proveedor> proveedor = jdbcTemplate.query(sql, CustomRowMapper.proveedorRowMapper, idProveedor);
        return (proveedor.isEmpty()) ? null : proveedor.getFirst();
    }

    public void guardarNuevoProveedor (Proveedor proveedor) {
        String sql = "INSERT INTO Proveedor (razon_social, contacto, RTN_contacto, telefono, correo, direccion) VALUES (?,?,?,?,?,?)";

        int rows = jdbcTemplate.update(sql, proveedor.getRazonSocial(), proveedor.getContacto(), proveedor.getRtnContacto(), proveedor.getTelefono(), proveedor.getCorreo(), proveedor.getDireccion());
        System.out.println(rows + " lineas afectadas");
    }

    public void eliminarProveedorPorId(int idProveedor) {
        String sql = "DELETE FROM Proveedor WHERE id_proveedor = ?";
        jdbcTemplate.update(sql, idProveedor);

    }

}
