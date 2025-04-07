package com.matasanos.model;


import java.util.List;

public class Persona {

    private Integer idPersona;// id_persona
    private String primerNombre;// primer_nombre
    private String segundoNombre;// segundo_nombre
    private String primerApellido;// primer_apellido
    private String segundoApellido;// segundo_apellido
    private String dni;// dni
    private Direccion direccion;
    private List<Correo> correos;
    private List<Telefono>  telefonos;

    public Persona() {}
     
    public Persona (Integer idPersona, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String dni, Direccion direccion) {
        this.idPersona = idPersona;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.dni = dni;
        this.direccion = direccion;
    }

    public Integer getIdPersona() { return idPersona; }

    public void setIdPersona(Integer idPersona ){
        this.idPersona = idPersona;
    }

    public String getPrimerNombre() { return primerNombre; }

    public void setPrimerNombre(String primerNombre ){
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() { return segundoNombre; }

    public void setSegundoNombre(String segundoNombre ){
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() { return primerApellido; }

    public void setPrimerApellido(String primerApellido ){
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() { return segundoApellido; }

    public void setSegundoApellido(String segundoApellido ){
        this.segundoApellido = segundoApellido;
    }

    public String getDni() { return dni; }

    public void setDni(String dni ){
        this.dni = dni;
    }

    public Direccion getDireccion() { return direccion; }

    public void setDireccion(Direccion direccion ){
        this.direccion = direccion;
    }

    public List<Correo> getCorreos() {
        return correos;
    }

    public void setCorreos(List<Correo> correos) {
        this.correos = correos;
    }

    public List<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<Telefono> telefonos) {
        this.telefonos = telefonos;
    }
}