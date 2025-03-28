package com.matasanos.model;



public class Correo {

    private int idCorreo;// id_correo
    private String correo;// correo
    private Persona persona;

    public Correo() {}
     
    public Correo (int idCorreo, String correo, Persona persona) {
        this.idCorreo = idCorreo;
        this.correo = correo;
        this.persona = persona;
    }

    public int getIdCorreo() { return idCorreo; }

    public void setIdCorreo(int idCorreo ){
        this.idCorreo = idCorreo;
    }

    public String getCorreo() { return correo; }

    public void setCorreo(String correo ){
        this.correo = correo;
    }

    public Persona getPersona() { return persona; }

    public void setPersona(Persona persona ){
        this.persona = persona;
    }
}