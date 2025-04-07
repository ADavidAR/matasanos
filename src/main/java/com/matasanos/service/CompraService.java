package com.matasanos.service;

import com.matasanos.repo.CompraRepo;
import com.matasanos.model.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static com.fasterxml.jackson.datatype.jsr310.DecimalUtils.toBigDecimal;


@Service
public class CompraService {
    private final CompraRepo compraRepo;

    public Compra compra(int idCompra) {
        return compraRepo.compra(idCompra);
    }
    public CompraService(CompraRepo compraRepo) {
        this.compraRepo = compraRepo;
    }
    public  List<Compra> listarCompras(){
        return compraRepo.listarCompras();
    }

    public List<Proveedor> listaProveedor(){
        return compraRepo.listarProveedores();
    }
    public List<Producto> listaProductos(int idProveedor){
        return compraRepo.listarProductoProveedor(idProveedor);
    }
    public void crearCompra(int idProveedor,List<Map<String,Object>> datos){
       Map<String,Object> datosCompra =datos.get(datos.size() - 1);
       datos.remove(datos.size() - 1);
        BigDecimal costo_total = calcularCostoTotal(datos);

        int idCompra=compraRepo.crearCompra(LocalDate.parse(datosCompra.get("fechaCompra").toString()),costo_total,idProveedor,(String) datosCompra.get("numFactura"));

        for (Map<String,Object> d :datos) {
            BigDecimal costo=new BigDecimal(d.get("costo").toString());
            int idPro=  (int)d.get("id");
            int cant=  (int) d.get("cantidad") ;

            compraRepo.creaProdcutoCompra(cant,costo,idCompra,idPro);
        }


    }
    public  List<ProductoCompra> lisarProductosCompra(int idCompra){
        return compraRepo.productosCompra(idCompra);
    }

    public BigDecimal calcularCostoTotal(List<Map<String,Object>> datos){
        BigDecimal total = BigDecimal.ZERO;

        for (Map<String,Object> d :datos){
            BigDecimal costo = new BigDecimal(d.get("costo").toString());
            BigDecimal cantidad = new BigDecimal(d.get("cantidad").toString());
            total = total.add(costo.multiply(cantidad));
    }
    return  total;
    }

}
