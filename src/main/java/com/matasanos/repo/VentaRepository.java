package com.matasanos.repo;

import java.util.List;

import com.matasanos.model.Categoria;
import com.matasanos.model.Departamento;
import com.matasanos.repo.rowmapper.CustomRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.matasanos.model.Producto;

@Repository
public class VentaRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ProductoRepo productoRepo;

    public VentaRepository(JdbcTemplate jdbcTemplate, ProductoRepo productoRepo) {
        this.jdbcTemplate = jdbcTemplate;
        this.productoRepo = productoRepo;
    }

    public List<Producto> listarProductosDeSucursal(int id){
        List<Producto> productos = this.productoRepo.listarProductosDeSucursal(id);
        for(var p : productos){
            System.out.println(p.getNombreProducto());
        }
        return this.productoRepo.listarProductosDeSucursal(id);
    }

    public List<Departamento> listarDepartamentos(){
        String sql = "SELECT id_departamento, nombre_departamento FROM Departamento";
        List<Departamento> departamentos = jdbcTemplate.query(sql, CustomRowMapper.departamentoRowMapper);

        return departamentos;
    }

    public List<Categoria> listarCategorias(){
        String sql = "Select c.id_categoria, c.nombre_categoria From Categoria c";
        List<Categoria> categorias = jdbcTemplate.query(sql, CustomRowMapper.categoriaSinDepartamentoRowMapper);

                return categorias;
    }

    public void procesarOrden(int cantidad, int idProducto, int idSucursal){
        String sql = "UPDATE FichaInventario SET cantidad = cantidad - ? WHERE id_producto = ? AND id_sucursal = ?";
        System.out.println("Ejecutando SQL: " + sql + " con cantidad: " + cantidad + " y id_producto: " + idProducto);
        jdbcTemplate.update(sql, cantidad, idProducto, idSucursal);
    }

}
