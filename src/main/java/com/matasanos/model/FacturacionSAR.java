package com.matasanos.model;

import java.time.LocalDate;

public class FacturacionSAR {

    private int idFacturaSar;// id_factura_sar
    private LocalDate fechaVigencia;// fecha_vigencia
    private int rangoInicio;// rango_inicio
    private int rangoFin;// rango_fin
    private boolean vigente;// vigente
    private String cai;// cai
    private String inicioCodFactura;// inicio_cod_factura

    public FacturacionSAR() {}
     
    public FacturacionSAR (int idFacturaSar, LocalDate fechaVigencia, int rangoInicio, int rangoFin, boolean vigente, String cai, String inicioCodFactura) {
        this.idFacturaSar = idFacturaSar;
        this.fechaVigencia = fechaVigencia;
        this.rangoInicio = rangoInicio;
        this.rangoFin = rangoFin;
        this.vigente = vigente;
        this.cai = cai;
        this.inicioCodFactura = inicioCodFactura;
    }

    public int getIdFacturaSar() { return idFacturaSar; }

    public void setIdFacturaSar(int idFacturaSar ){
        this.idFacturaSar = idFacturaSar;
    }

    public LocalDate getFechaVigencia() { return fechaVigencia; }

    public void setFechaVigencia(LocalDate fechaVigencia ){
        this.fechaVigencia = fechaVigencia;
    }

    public int getRangoInicio() { return rangoInicio; }

    public void setRangoInicio(int rangoInicio ){
        this.rangoInicio = rangoInicio;
    }

    public int getRangoFin() { return rangoFin; }

    public void setRangoFin(int rangoFin ){
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

    public String getInicioCodFactura() { return inicioCodFactura; }

    public void setInicioCodFactura(String inicioCodFactura ){
        this.inicioCodFactura = inicioCodFactura;
    }
}