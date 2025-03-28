package com.matasanos.model;



public class Telefono {

    private int idTelefono;// id_telefono
    private String telefono;// telefono
    private Persona persona;

    public Telefono() {}
     
    public Telefono (int idTelefono, String telefono, Persona persona) {
        this.idTelefono = idTelefono;
        this.telefono = telefono;
        this.persona = persona;
    }

    public int getIdTelefono() { return idTelefono; }

    public void setIdTelefono(int idTelefono ){
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