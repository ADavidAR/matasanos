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
    //public  class Compra CreaCompra()

        public void CreaCompra(){

        }

        public  void ActualizaCompra(){

        }

       /* public List<Compra> listarCompras(){
        String sql= "select * from Compra";
        return jdbcTemplate.query(sql, CustomRowMapper.compraRowMapper);
        }*/

}
