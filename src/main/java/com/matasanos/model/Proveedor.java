package com.matasanos.model;



public class Proveedor {

    private int idProveedor;// id_proveedor
    private String razonSocial;// razon_social
    private String contacto;// contacto
    private String rtnContacto;// RTN_contacto
    private String telefono;// telefono
    private String correo;// correo
    private String direccion;// direccion

    public Proveedor() {}
     
    public Proveedor (
            int idProveedor,
            String razonSocial,
            String contacto,
            String rtnContacto,
            String telefono,
            String correo,
            String direccion
    ) {
        this.idProveedor = idProveedor;
        this.razonSocial = razonSocial;
        this.contacto = contacto;
        this.rtnContacto = rtnContacto;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
    }

    public Proveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getIdProveedor() { return idProveedor; }

    public void setIdProveedor(int idProveedor ){
        this.idProveedor = idProveedor;
    }

    public String getRazonSocial() { return razonSocial; }

    public void setRazonSocial(String razonSocial ){
        this.razonSocial = razonSocial;
    }

    public String getContacto() { return contacto; }

    public void setContacto(String contacto ){
        this.contacto = contacto;
    }

    public String getRtnContacto() { return rtnContacto; }

    public void setRtnContacto(String rtnContacto ){
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