package com.matasanos.model;



public class Persona {

    private int idPersona;// id_persona
    private String primerNombre;// primer_nombre
    private String segundoNombre;// segundo_nombre
    private String primerApellido;// primer_apellido
    private String segundoApellido;// segundo_apellido
    private String dni;// dni
    private Direccion direccion;

    public Persona() {}
     
    public Persona (int idPersona, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String dni, Direccion direccion) {
        this.idPersona = idPersona;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.dni = dni;
        this.direccion = direccion;
    }

    public int getIdPersona() { return idPersona; }

    public void setIdPersona(int idPersona ){
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
}