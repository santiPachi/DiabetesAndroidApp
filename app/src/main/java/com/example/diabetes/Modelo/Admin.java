package com.example.diabetes.Modelo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Admin implements Serializable {
    @SerializedName("id_adm")
    private Integer id_adm;
    @SerializedName("nombre_adm")
    private String nombre_adm;
    @SerializedName("tipoUsuario")
    private String tipoUsuario;
    @SerializedName("cedula_adm")
    private String cedula_adm;
    @SerializedName("apellido_adm")
    private String apellido_adm;
    @SerializedName("nombreUsuario")
    private String nombreUsuario;
    @SerializedName("telefono_adm")
    private String telefono_adm;
    public Admin(){}

    public Admin(Integer id_adm, String nombre_adm, String tipoUsuario, String cedula_adm, String apellido_adm, String nombreUsuario, String telefono_adm) {
        this.id_adm = id_adm;
        this.nombre_adm = nombre_adm;
        this.tipoUsuario = tipoUsuario;
        this.cedula_adm = cedula_adm;
        this.apellido_adm = apellido_adm;
        this.nombreUsuario = nombreUsuario;
        this.telefono_adm = telefono_adm;
    }

    public Integer getId_adm() {
        return id_adm;
    }

    public void setId_adm(Integer id_adm) {
        this.id_adm = id_adm;
    }

    public String getNombre_adm() {
        return nombre_adm;
    }

    public void setNombre_adm(String nombre_adm) {
        this.nombre_adm = nombre_adm;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getCedula_adm() {
        return cedula_adm;
    }

    public void setCedula_adm(String cedula_adm) {
        this.cedula_adm = cedula_adm;
    }

    public String getApellido_adm() {
        return apellido_adm;
    }

    public void setApellido_adm(String apellido_adm) {
        this.apellido_adm = apellido_adm;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getTelefono_adm() {
        return telefono_adm;
    }

    public void setTelefono_adm(String telefono_adm) {
        this.telefono_adm = telefono_adm;
    }
}
