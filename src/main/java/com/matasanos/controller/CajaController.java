package com.matasanos.controller;

import com.matasanos.model.Caja;
import com.matasanos.service.CajaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cajas")
public class CajaController {

    private CajaService cajaService;

    public CajaController(CajaService cajaService) {
        this.cajaService = cajaService;
    }

    @GetMapping("")
    public List<Caja> listarSucursales() {
        return cajaService.listarCajas();
    }

    @GetMapping("/sucursal/{idSucursal}")
    public List<Caja> listarSucursalesPorSucursal(@PathVariable int idSucursal) {
        return cajaService.listarCajasPorSucursal(idSucursal);
    }

    @PostMapping(path = {"/verificar/edicion", "/verificar/crear"})
    public ResponseEntity<?> validarModificacion(@RequestBody Caja caja) {

        Map<String, Object> response = new HashMap<>();
        if(cajaService.verificarNumCaja(caja.getNumCaja(), caja.getSucursal().getIdSucursal())) {
            response.put("msg", "caja existente");
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(response);
    }


    @GetMapping("/verificar/eliminar/{idCaja}")
    public ResponseEntity<?> verificarEliminar(@PathVariable int idCaja) {
        Map<String, Object> response = new HashMap<>();

        if(cajaService.enUso(idCaja)) {
            response.put("msg", "caja en uso");
            return ResponseEntity.badRequest().body(response);
        }

        if(!cajaService.existe(idCaja)) {
            response.put("msg", "sucursal inexistente");
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/eliminar/{idSucursal}")
    public ResponseEntity<?> eliminar(@PathVariable int idSucursal) {
        cajaService.eliminarSucursal(idSucursal);
        return ResponseEntity.ok(Collections.emptyMap());
    }

    @PostMapping("/modificar")
    public void modificar(@RequestBody Caja caja) {
        cajaService.modificar(caja);
    }

    @PostMapping("/agregar")
    public void agregar(@RequestBody Caja caja) {
        cajaService.agregar(caja);
    }
}
