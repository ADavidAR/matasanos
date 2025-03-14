package com.matasanos.model;



public class Proveedor {

    private int idProveedor;
    private String razonSocial;
    private String contacto;
    private String rtnContacto;
    private String telefono;
    private String correo;
    private String direccion;

    public Proveedor (int idProveedor, String razonSocial, String contacto, String rtnContacto, String telefono, String correo, String direccion) {
        this.idProveedor = idProveedor;
        this.razonSocial = razonSocial;
        this.contacto = contacto;
        this.rtnContacto = rtnContacto;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
    }

    public int getIdproveedor() { return idProveedor; }

    public void setIdproveedor(int idProveedor ){
        this.idProveedor = idProveedor;
    }

    public String getRazonsocial() { return razonSocial; }

    public void setRazonsocial(String razonSocial ){
        this.razonSocial = razonSocial;
    }

    public String getContacto() { return contacto; }

    public void setContacto(String contacto ){
        this.contacto = contacto;
    }

    public String getRtncontacto() { return rtnContacto; }

    public void setRtncontacto(String rtnContacto ){
        this.rtnContacto = rtnContacto;
    }

    public String getTelefono() { return telefono; }

    public void setTelefono(String telefono ){
        this.telefono = telefono;
    }

    public String getCorreo() { return correo; }

    public void setCorreo(String correo ){
        this.correo = correo;
    }

    public String getDireccion() { return direccion; }

    public void setDireccion(String direccion ){
        this.direccion = direccion;
    }
}