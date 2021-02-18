package com.example.diabetes.Modelo;

import com.google.gson.annotations.SerializedName;

public class Usuario {

    @SerializedName("id_doc")
    private Integer id;
    @SerializedName("nombre_doc")
    private String nombre_doc;
    @SerializedName("tipoUsuario")
    private String tipoUsuario;
    @SerializedName("cedula_doc")
    private String cedula_doc;
    @SerializedName("apellido_doc")
    private String apellido_doc;
    @SerializedName("nombreUsuario")
    private String nombreUsuario;
    @SerializedName("telefono_doc")
    private String telefono_doc;
    public Usuario(){}

    public Usuario(Integer id, String nombre_doc, String tipoUsuario, String cedula_doc, String apellido_doc, String nombreUsuario, String telefono_doc) {
        this.id = id;
        this.nombre_doc = nombre_doc;
        this.tipoUsuario = tipoUsuario;
        this.cedula_doc = cedula_doc;
        this.apellido_doc = apellido_doc;
        this.nombreUsuario = nombreUsuario;
        this.telefono_doc = telefono_doc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre_doc() {
        return nombre_doc;
    }

    public void setNombre_doc(String nombre_doc) {
        this.nombre_doc = nombre_doc;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getCedula_doc() {
        return cedula_doc;
    }

    public void setCedula_doc(String cedula_doc) {
        this.cedula_doc = cedula_doc;
    }

    public String getApellido_doc() {
        return apellido_doc;
    }

    public void setApellido_doc(String apellido_doc) {
        this.apellido_doc = apellido_doc;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getTelefono_doc() {
        return telefono_doc;
    }

    public void setTelefono_doc(String telefono_doc) {
        this.telefono_doc = telefono_doc;
    }
}
