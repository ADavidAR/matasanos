package com.matasanos.repo;

import com.matasanos.repo.rowmapper.CustomRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import  com.matasanos.model.*;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.List;

@Repository
public class CompraRepo {
    JdbcTemplate jdbcTemplate;
    public CompraRepo(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }
    //public  class Compra creaCompra()

        public void creaCompra(){

        }

        public  void actualizaCompra(){

        }

        public List<Compra> listarCompras(){
        String sql= "select * from Compra";
        return jdbcTemplate.query(sql, CustomRowMapper.compraRowMapper);
        }
        public  List<Proveedor> listarProveedores() {
        String sql= "select * from Proveedor";
        return jdbcTemplate.query(sql, CustomRowMapper.proveedorRowMapper);
    }
        public List<Producto> listarProductoProveedor(int idProveedor){
        String sql="SELECT p.*, d.id_departamento, nombre_categoria, nombre_departamento,pv.*  FROM Producto p "+
            "INNER JOIN Categoria c ON c.id_categoria = p.id_categoria "+
            "INNER JOIN Departamento d ON d.id_departamento = c.id_departamento "+
            "INNER JOIN Proveedor pv ON pv.id_proveedor=p.id_proveedor where pv.id_proveedor = ?";
        return jdbcTemplate.query(sql,CustomRowMapper.productoRowMapper,idProveedor);
        }
        public  void  crearCompra(){
        String sql= ""
        }

}
