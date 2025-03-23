package com.matasanos.model;



public class Permiso {

    private int idPermiso; // id_permiso
    private String descripcion; // descripcion
    private String pantallaHtml; // pantalla_html

    public Permiso (int idPermiso, String descripcion, String pantallaHtml) {
        this.idPermiso = idPermiso;
        this.descripcion = descripcion;
        this.pantallaHtml = pantallaHtml;
    }

    public int getIdpermiso() { return idPermiso; }

    public void setIdpermiso(int idPermiso ){
        this.idPermiso = idPermiso;
    }

    public String getDescripcion() { return descripcion; }

    public void setDescripcion(String descripcion ){
        this.descripcion = descripcion;
    }

    public String getPantallahtml() { return pantallaHtml; }

    public void setPantallahtml(String pantallaHtml ){
        this.pantallaHtml = pantallaHtml;
    }
}