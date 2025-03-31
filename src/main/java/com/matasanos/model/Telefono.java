package com.matasanos.model;



public class Telefono {

    private Integer idTelefono;// id_telefono
    private String telefono;// telefono
    private Persona persona;

    public Telefono() {}
     
    public Telefono (Integer idTelefono, String telefono, Persona persona) {
        this.idTelefono = idTelefono;
        this.telefono = telefono;
        this.persona = persona;
    }

    public Integer getIdTelefono() { return idTelefono; }

    public void setIdTelefono(Integer idTelefono ){
        this.idTelefono = idTelefono;
    }

    public String getTelefono() { return telefono; }

    public void setTelefono(String telefono ){
        this.telefono = telefono;
    }

    public Persona getPersona() { return persona; }

    public void setPersona(Persona persona ){
        this.persona = persona;
    }
}