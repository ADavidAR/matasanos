package com.matasanos.service;


import com.matasanos.model.*;
import com.matasanos.repo.EmpleadoRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
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

        if(idEmpleado==0) {
        int idDireccion;
            int idPersona = empleadoRepo.obtenerIdPersona((String) empleado.get("dni"));
            if (idPersona == 0) {
                 idDireccion = empleadoRepo.crearDireccion((String) empleado.get("referencia"), (int) empleado.get("idColonia"));
                idPersona = empleadoRepo.crearPersona((String) empleado.get("primerNombre"), (String) empleado.get("segundoNombre"), (String) empleado.get("primerApellido"), (String) empleado.get("segundoApellido"), (String) empleado.get("dni"), idDireccion);
            }
             idDireccion=empleadoRepo.obtenerIdDireccion((String) empleado.get("dni"));
            if (idDireccion==0) {
                idDireccion = empleadoRepo.crearDireccion((String) empleado.get("referencia"), (int) empleado.get("idColonia"));

                empleadoRepo.actualizarPersonaDireccion( (String) empleado.get("dni"), idDireccion);
            }
            if (!empleadoRepo.tieneCorreo(idPersona)) empleadoRepo.crearCorreo(idPersona, (String) empleado.get("correo"));
            if(!empleadoRepo.tieneTelefono(idPersona)) empleadoRepo.crearTelefono(idPersona, (String) empleado.get("telefono"));

            empleadoRepo.crearEmpleado(new BigDecimal(empleado.get("salario").toString()), LocalDate.parse(empleado.get("fechaContratacion").toString()), (int) empleado.get("idSucursal"), idPersona, (int) empleado.get("idCargo"), (int) empleado.get("idUsuario"));
        }else{
            throw new IllegalArgumentException("el empleado ya esta agregado");
        }
    }
    public List<Object> datosPersona(String dni){
        List<Object> datos = new ArrayList<>();
        datos.add(empleadoRepo.obtenerPersona(dni));
        int idPersona= empleadoRepo.obtenerIdPersona(dni);


        datos.add(empleadoRepo.obtenerCorreo(idPersona));
        datos.add(empleadoRepo.obtenerTelefono(idPersona));
        return datos;
    }

    public List<Empleado> listarEmpleados(){
        return empleadoRepo.listarEmpleados();
    }
    public  Empleado ObtenerEmpleado( int idEmpleado){

        return empleadoRepo.obtenerEmpleado(idEmpleado);
    }
    public void actualizarEmpleado(int idEmpleado, Map<String,Object> empleado){
        int idPersona =empleadoRepo.obtenerIdPersona(idEmpleado);
        empleadoRepo.actualizarPersona((String) empleado.get("primerNombre"), (String) empleado.get("segundoNombre"), (String) empleado.get("primerApellido"), (String) empleado.get("segundoApellido"), (String) empleado.get("dni"),idPersona);
        int idDireccion=empleadoRepo.obtenerIdDireccion((String) empleado.get("dni"));
        empleadoRepo.actualizarDireccion( idDireccion,(int) empleado.get("idColonia"),(String) empleado.get("referencia"));
        empleadoRepo.actualizarEmpleado(new BigDecimal(empleado.get("salario").toString()),LocalDate.parse(empleado.get("fechaContratacion").toString()),(int) empleado.get("idCargo"),(int) empleado.get("idUsuario"),(int) empleado.get("idSucursal"),idEmpleado);
        empleadoRepo.actualizarCorreo(idPersona,(String) empleado.get("correo"));
        empleadoRepo.actualizarTelefono(idPersona,(String) empleado.get("telefono"));

    }
    public  void  eliminarEmpleado(int idEmpleado){
        empleadoRepo.BorrarEmpleado(idEmpleado);
    }
}

