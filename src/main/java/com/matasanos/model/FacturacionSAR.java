package com.matasanos.model;

import java.time.LocalDate;

public class FacturacionSAR {

    private Integer idFacturaSar;// id_factura_sar
    private Sucursal sucursal;
    private LocalDate fechaVigencia;// fecha_vigencia
    private Integer rangoInicio;// rango_inicio
    private Integer rangoFin;// rango_fin
    private Boolean vigente;// vigente
    private String cai;// cai
    private String inicioCodFactura;// inicio_cod_factura

    public FacturacionSAR() {}
     
    public FacturacionSAR (Integer idFacturaSar, Sucursal sucursal, LocalDate fechaVigencia, Integer rangoInicio, Integer rangoFin, Boolean vigente, String cai, String inicioCodFactura) {
        this.idFacturaSar = idFacturaSar;
        this.sucursal = sucursal;
        this.fechaVigencia = fechaVigencia;
        this.rangoInicio = rangoInicio;
        this.rangoFin = rangoFin;
        this.vigente = vigente;
        this.cai = cai;
        this.inicioCodFactura = inicioCodFactura;
    }

    public Integer getIdFacturaSar() { return idFacturaSar; }

    public void setIdFacturaSar(Integer idFacturaSar ){
        this.idFacturaSar = idFacturaSar;
    }

    public Sucursal getSucursal() { return sucursal; }

    public void setSucursal(Sucursal sucursal ){
        this.sucursal = sucursal;
    }

    public LocalDate getFechaVigencia() { return fechaVigencia; }

    public void setFechaVigencia(LocalDate fechaVigencia ){
        this.fechaVigencia = fechaVigencia;
    }

    public Integer getRangoInicio() { return rangoInicio; }

    public void setRangoInicio(Integer rangoInicio ){
        this.rangoInicio = rangoInicio;
    }

    public Integer getRangoFin() { return rangoFin; }

    public void setRangoFin(Integer rangoFin ){
        this.rangoFin = rangoFin;
    }

    public Boolean getVigente() { return vigente; }

    public void setVigente(Boolean vigente ){
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