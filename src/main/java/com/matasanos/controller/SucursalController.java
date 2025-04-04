package com.matasanos.controller;

import com.matasanos.model.Sucursal;
import com.matasanos.service.SucursalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sucursales")
public class SucursalController {

    private SucursalService sucursalService;

    public SucursalController(SucursalService sucursalService) {
        this.sucursalService = sucursalService;
    }

    @GetMapping("")
    public List<Sucursal> listarSucursales() {
        return sucursalService.listarSucursales();
    }

    @PostMapping("/verificar/edicion")
    public ResponseEntity<?> validarModificacion(@RequestBody Sucursal sucursal) {

        Map<String, Object> response = new HashMap<>();
        if(sucursalService.verificarModificacionNombre(sucursal.getNombreSucursal(), sucursal.getIdSucursal())) {
            response.put("msg", "sucursal existente");
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/verificar/crear")
    public ResponseEntity<?> validarCreacion(@RequestBody Sucursal sucursal) {

        Map<String, Object> response = new HashMap<>();
        if(sucursalService.verificarCreacionNombre(sucursal.getNombreSucursal())) {
            response.put("msg", "sucursal existente");
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{idSucursal}")
    public ResponseEntity<?> obtenerSucursalDireccion(@PathVariable int idSucursal) {
        Sucursal s = sucursalService.obtenerSucursalDireccion(idSucursal);

        return ( s != null ) ? ResponseEntity.ok(s) : ResponseEntity.ok(Collections.emptyMap());
    }

    @GetMapping("/verificar/eliminar/{idSucursal}")
    public ResponseEntity<?> verificarEliminar(@PathVariable int idSucursal) {
        Map<String, Object> response = new HashMap<>();

        if(sucursalService.enUso(idSucursal)) {
            response.put("msg", "sucursal en uso");
            return ResponseEntity.badRequest().body(response);
        }

        if(!sucursalService.existe(idSucursal)) {
            response.put("msg", "sucursal inexistente");
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/eliminar/{idSucursal}")
    public ResponseEntity<?> eliminar(@PathVariable int idSucursal) {
        sucursalService.eliminarSucursal(idSucursal);
        return ResponseEntity.ok(Collections.emptyMap());
    }

    @PostMapping("/modificar")
    public void modificar(@RequestBody Sucursal sucursal) {
        sucursalService.modificarSucursal(sucursal);
    }

    @PostMapping("/agregar")
    public void agregar(@RequestBody Sucursal sucursal) {
        sucursalService.agregarSucursal(sucursal);
    }
}
