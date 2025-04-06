package com.matasanos.repo;


import com.matasanos.model.Cliente;
import com.matasanos.repo.rowmapper.CustomRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClienteRepo {

    JdbcTemplate jdbcTemplate;
    PersonaRepo personaRepo;

    public ClienteRepo(JdbcTemplate jdbcTemplate, PersonaRepo personaRepo) {
        this.jdbcTemplate = jdbcTemplate;
        this.personaRepo = personaRepo;
    }

    public List<Cliente> listarClientes() {
        String sql = "SELECT id_persona, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, dni, id_cliente, rtn, fecha_creacion FROM v_ClientePersona";

        return jdbcTemplate.query(sql, CustomRowMapper.clienteSinDireccionRowMapper);
    }

    public boolean enUso(int idCliente) {
        String sql = "SELECT 1 AS tabla FROM Factura WHERE id_cliente = ? UNION ALL SELECT 2 AS tabla FROM Receta WHERE id_cliente = ?";

        return !jdbcTemplate.queryForList(
                sql,
                Integer.class,
                idCliente,
                idCliente
                ).isEmpty();
    }

    public boolean existe(int idCliente) {
        String sql = "SELECT 'c' FROM Cliente WHERE id_cliente = ?";
        return !jdbcTemplate.queryForList(sql, String.class, idCliente).isEmpty();
    }

    public void eliminar(int idCliente) {
        String sql = "DELETE FROM Cliente WHERE id_cliente = ?";
        jdbcTemplate.update(sql, idCliente);
    }

    public void modificar(Cliente cliente) {
        String sqlDireccion = "UPDATE Direccion SET referencia = ?, id_colonia = ? WHERE id_direccion = ?";

        jdbcTemplate.update(
                sqlDireccion,
                cliente.getPersona().getDireccion().getReferencia(),
                cliente.getPersona().getDireccion().getColonia().getIdColonia(),
                cliente.getPersona().getDireccion().getIdDireccion()
        );

        personaRepo.modificar(cliente.getPersona());

        String sqlCliente = "UPDATE Cliente SET rtn = ?, fecha_modificacion = GETDATE(), id_usuario_modificacion = ? WHERE id_cliente = ?";

        jdbcTemplate.update(
                sqlCliente,
                cliente.getRtn(),
                cliente.getIdUsuarioModificacion(),
                cliente.getIdCliente()
        );
    }

    public void agregarClienteConNuevaPersona(Cliente cliente) {
        int idPersona = personaRepo.agregar(cliente.getPersona());

        String sqlCliente = "INSERT INTO Cliente (fecha_creacion, rtn, id_usuario_creacion, id_persona) VALUES (GETDATE(), ?, ?, ?)";

        jdbcTemplate.update(sqlCliente,
                cliente.getRtn(),
                cliente.getIdUsuarioCreacion(),
                idPersona
        );
    }

    public void agregarCLienteModificandoPersonaExistente(Cliente cliente) {
        personaRepo.modificar(cliente.getPersona());

        String sqlCliente = "INSERT INTO Cliente (fecha_creacion, rtn, id_usuario_creacion, id_persona) VALUES (GETDATE(), ?, ?, ?)";

        jdbcTemplate.update(sqlCliente,
                cliente.getRtn(),
                cliente.getIdUsuarioCreacion(),
                cliente.getPersona().getIdPersona()
        );
    }

    public void agregarClienteConPersonaExistente(Cliente cliente) {
        String sqlCliente = "INSERT INTO Cliente (fecha_creacion, rtn, id_usuario_creacion, id_persona) VALUES (GETDATE(), ?, ?, ?)";

        jdbcTemplate.update(sqlCliente,
                cliente.getRtn(),
                cliente.getIdUsuarioCreacion(),
                cliente.getPersona().getIdPersona()
        );
    }

    public Cliente obtenerClientePorId(int idCliente) {
        String sql = "SELECT id_persona, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, dni, id_cliente, rtn, fecha_creacion, id_direccion, referencia, id_colonia, nombre_colonia, id_ciudad, ciudad FROM v_ClientePersona WHERE id_cliente = ?";

        List<Cliente> cliente = jdbcTemplate.query(sql, CustomRowMapper.clienteConDireccionRowMapper, idCliente);

        return cliente.isEmpty() ? null : cliente.getFirst();
    }

    public Cliente obtenerClientePorDni(String dni) {
        String sql = "SELECT id_persona, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, dni, id_cliente, rtn, fecha_creacion FROM v_ClientePersona WHERE dni = ?";

        List<Cliente> cliente = jdbcTemplate.query(sql, CustomRowMapper.clienteSinDireccionRowMapper, dni);

        return cliente.isEmpty() ? null : cliente.getFirst();
    }

    public Cliente obtenerClientePorRtn(String rtn) {
        String sql = "SELECT id_persona, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, dni, id_cliente, rtn, fecha_creacion, id_direccion, referencia, id_colonia, nombre_colonia, id_ciudad, ciudad FROM v_ClientePersona WHERE rtn = ?";

        List<Cliente> cliente = jdbcTemplate.query(sql, CustomRowMapper.clienteSinDireccionRowMapper, rtn);

        return cliente.isEmpty() ? null : cliente.getFirst();
    }
}
