package com.matasanos.model;

import java.time.LocalDate;

public class FacturacionSAR {

    private int idFacturaSar;
    private LocalDate fechaVigencia;
    private int rangoInicio;
    private int rangoFin;
    private boolean vigente;
    private String cai;

    public FacturacionSAR (int idFacturaSar, LocalDate fechaVigencia, int rangoInicio, int rangoFin, boolean vigente, String cai) {
        this.idFacturaSar = idFacturaSar;
        this.fechaVigencia = fechaVigencia;
        this.rangoInicio = rangoInicio;
        this.rangoFin = rangoFin;
        this.vigente = vigente;
        this.cai = cai;
    }

    public int getIdfacturasar() { return idFacturaSar; }

    public void setIdfacturasar(int idFacturaSar ){
        this.idFacturaSar = idFacturaSar;
    }

    public LocalDate getFechavigencia() { return fechaVigencia; }

    public void setFechavigencia(LocalDate fechaVigencia ){
        this.fechaVigencia = fechaVigencia;
    }

    public int getRangoinicio() { return rangoInicio; }

    public void setRangoinicio(int rangoInicio ){
        this.rangoInicio = rangoInicio;
    }

    public int getRangofin() { return rangoFin; }

    public void setRangofin(int rangoFin ){
        this.rangoFin = rangoFin;
    }

    public boolean getVigente() { return vigente; }

    public void setVigente(boolean vigente ){
        this.vigente = vigente;
    }

    public String getCai() { return cai; }

    public void setCai(String cai ){
        this.cai = cai;
    }
}