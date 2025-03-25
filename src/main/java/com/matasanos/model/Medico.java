package com.matasanos.model;



public class Medico {

    private int idMedico;// id_medico
    private String primerNombre;// primer_nombre
    private String segundoNombre;// segundo_nombre
    private String primerApellido;// primer_apellido
    private String segundoApellido;// segundo_apellido
    private String numColegiado;// num_colegiado

    public Medico() {}
     
    public Medico (int idMedico, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String numColegiado) {
        this.idMedico = idMedico;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.numColegiado = numColegiado;
    }

    public int getIdMedico() { return idMedico; }

    public void setIdMedico(int idMedico ){
        this.idMedico = idMedico;
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

    public String getNumColegiado() { return numColegiado; }

    public void setNumColegiado(String numColegiado ){
        this.numColegiado = numColegiado;
    }
}