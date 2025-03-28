package com.matasanos.model;



public class Medico {

    private int idMedico;// id_medico
    private String numColegiado;// num_colegiado
    private int idPersona;// id_persona

    public Medico() {}
     
    public Medico (int idMedico, String numColegiado, int idPersona) {
        this.idMedico = idMedico;
        this.numColegiado = numColegiado;
        this.idPersona = idPersona;
    }

    public int getIdMedico() { return idMedico; }

    public void setIdMedico(int idMedico ){
        this.idMedico = idMedico;
    }

    public String getNumColegiado() { return numColegiado; }

    public void setNumColegiado(String numColegiado ){
        this.numColegiado = numColegiado;
    }

    public int getIdPersona() { return idPersona; }

    public void setIdPersona(int idPersona ){
        this.idPersona = idPersona;
    }
}