package com.matasanos.service;

import com.matasanos.repo.CompraRepo;
import com.matasanos.model.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static com.fasterxml.jackson.datatype.jsr310.DecimalUtils.toBigDecimal;


@Service
public class CompraService {
    private final CompraRepo compraRepo;


    public CompraService(CompraRepo compraRepo) {
        this.compraRepo = compraRepo;
    }
    public  List<Compra> comprasPendientes(){
        return compraRepo.comprasPendientes();
    }

    public List<Proveedor> listaProveedor(){
        return compraRepo.listarProveedores();
    }
    public List<Producto> listaProductos(int idProveedor){
        return compraRepo.listarProductoProveedor(idProveedor);
    }
    public void crearSolicitudCompra(int idProveedor,List<Map<String,Object>> datos){
       BigDecimal costo_total = calcularCostoTotal(datos);
        int idCompra=compraRepo.crearCompra(costo_total,idProveedor);

        for (Map<String,Object> d :datos) {
            BigDecimal costo=toBigDecimal(d.get("costo"));
            int idPro=  toBigDecimal(d.get("id")).intValue();
            int cant=  toBigDecimal(d.get("cantidad")).intValue() ;

            compraRepo.creaProdcutoCompra(cant,costo,idCompra,idPro);
        }


    }
    public  List<ProductoCompra> lisarProductosCompra(int idCompra){
        return compraRepo.productosCompra(idCompra);
    }

    public BigDecimal calcularCostoTotal(List<Map<String,Object>> datos){
        BigDecimal total = BigDecimal.ZERO;

        for (Map<String,Object> d :datos){
            BigDecimal costo = toBigDecimal(d.get("costo"));
            BigDecimal cantidad = toBigDecimal(d.get("cantidad"));
            total = total.add(costo.multiply(cantidad));
    }
    return  total;
    }
    private BigDecimal toBigDecimal(Object value) {
        if (value == null) {
            return BigDecimal.ZERO;
        }
        if (value instanceof BigDecimal) {
            return (BigDecimal) value;
        }
        try {
            return new BigDecimal(value.toString());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Valor no convertible a BigDecimal: " + value);
        }
    }
}
