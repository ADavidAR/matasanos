package com.matasanos.controller;
import com.matasanos.model.*;

import com.matasanos.service.CompraService;
import com.matasanos.service.EmpleadoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/empleados")

public class EmpleadoController {
    private final EmpleadoService empleadoService;


    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }
    @GetMapping("/cargarCiudades")
    @ResponseBody
    public List<Ciudad> listarCiudades(){
        return  empleadoService.listarCiudades();
    }
    @GetMapping("/cargarCargos")
    @ResponseBody
    public List<Cargo> listarCargos(){
        return  empleadoService.listarCargos();
    }
    @GetMapping("/cargarSucursales")
    @ResponseBody
    public List<Sucursal> listarSucursales(){
        return  empleadoService.listarSucursales();
    }
    @GetMapping("/cargarColonias/{idCiudad}")
    @ResponseBody
    public List<Colonia> listarColonias(@PathVariable  int idCiudad){

        return  empleadoService.listarColonias(idCiudad);
    }
    @PostMapping("/crearEmpleado")
    public  void  crearEmpleado(@RequestBody Map<String,Object> empleado){
        empleadoService.crearEmpleado(empleado);
    }
    @GetMapping("/cargarDatosPersona/{dni}")
    public  Persona datosPersona(@PathVariable String dni){
     return new Persona();
    }
}
