package com.matasanos.model;



public class Medico {

    private Integer idMedico;// id_medico
    private String numColegiado;// num_colegiado
    private Integer idPersona;// id_persona

    public Medico() {}
     
    public Medico (Integer idMedico, String numColegiado, Integer idPersona) {
        this.idMedico = idMedico;
        this.numColegiado = numColegiado;
        this.idPersona = idPersona;
    }

    public Integer getIdMedico() { return idMedico; }

    public void setIdMedico(Integer idMedico ){
        this.idMedico = idMedico;
    }

    public String getNumColegiado() { return numColegiado; }

    public void setNumColegiado(String numColegiado ){
        this.numColegiado = numColegiado;
    }

    public Integer getIdPersona() { return idPersona; }

    public void setIdPersona(Integer idPersona ){
        this.idPersona = idPersona;
    }
}