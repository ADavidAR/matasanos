package com.matasanos.service;


import com.matasanos.model.*;
import com.matasanos.repo.EmpleadoRepo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.fasterxml.jackson.datatype.jsr310.DecimalUtils.toBigDecimal;


@Service
public class EmpleadoService {
    private final EmpleadoRepo empleadoRepo;


    public EmpleadoService(EmpleadoRepo empleadoRepo) {
        this.empleadoRepo = empleadoRepo;
    }

    public List<Ciudad> listarCiudades(){
        return  empleadoRepo.listarCiudad();
    }
    public List<Cargo> listarCargos(){
        return  empleadoRepo.listarCargos();
    }
    public List<Sucursal> listarSucursales(){
        return  empleadoRepo.listarSucursal();
    }
    public List<Colonia> listarColonias(int idCiudad){
        return  empleadoRepo.listarColonias(idCiudad);
    }
    public void crearEmpleado(Map<String,Object>empleado){
        int idEmpleado=empleadoRepo.obtenerIdEmpleado((String) empleado.get("dni") );
        System.out.println(idEmpleado);
        if(idEmpleado==0) {
            int idPersona = empleadoRepo.obtenerIdPersona((String) empleado.get("dni"));
            if (idPersona == 0) {
                int idDireccion = empleadoRepo.crearDireccion((String) empleado.get("referencia"), (int) empleado.get("idColonia"));
                idPersona = empleadoRepo.crearPersona((String) empleado.get("primerNombre"), (String) empleado.get("segundoNombre"), (String) empleado.get("primerApellido"), (String) empleado.get(" segundoApellido"), (String) empleado.get("dni"), idDireccion);
                empleadoRepo.crearCorreo(idPersona, (String) empleado.get("correo"));
                empleadoRepo.crearTelefono(idPersona, (String) empleado.get("telefono"));
            }

            empleadoRepo.crearEmpleado(new BigDecimal(empleado.get("salario").toString()), LocalDate.parse(empleado.get("fechaContratacion").toString()), (int) empleado.get("idSucursal"), idPersona, (int) empleado.get("idCargo"), (int) empleado.get("idUsuario"));
        }else{
            throw new IllegalArgumentException("el empleado ya esta agregado");
        }
    }
    public Persona datosPersona(String dni){

    }
}

