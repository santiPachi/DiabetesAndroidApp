package com.example.diabetes.Modelo;

import com.google.gson.annotations.SerializedName ;

import java.io.Serializable;

public class Paciente implements Serializable {

    @SerializedName("id_pac")
    private Integer id_pac;
    @SerializedName("tipoUsuario")
    private String tipoUsuario;
    @SerializedName("cedula_pac")
    private String cedula_pac;
    @SerializedName("nombre_pac")
    private String nombre_pac;
    @SerializedName("urgente")
    private Integer urgente;
    @SerializedName("apellido_pac")
    private String apellido_pac;
    @SerializedName("nombreUsuario")
    private String nombreUsuario;
    @SerializedName("telefono_pac")
    private String telefono_pac;
    @SerializedName("nivelGlucosa")
    private double nivelGlucosa;
    @SerializedName("edad_pac")
    private int edad_pac;
    @SerializedName("sexo_pac")
    private String sexo_pac;
    @SerializedName("password")
    private String password;
    @SerializedName(("id_doc"))
    private Integer id_doc;
    public Paciente(){};

    public Paciente(Integer id_pac, String tipoUsuario, String cedula_pac, String nombre_pac, Integer urgente, String apellido_pac, String nombreUsuario, String telefono_pac, double nivelGlucosa, int edad_pac, String sexo_pac, Integer id_doc,String password) {
        this.id_pac = id_pac;
        this.tipoUsuario = tipoUsuario;
        this.cedula_pac = cedula_pac;
        this.nombre_pac = nombre_pac;
        this.urgente = urgente;
        this.apellido_pac = apellido_pac;
        this.nombreUsuario = nombreUsuario;
        this.telefono_pac = telefono_pac;
        this.nivelGlucosa = nivelGlucosa;
        this.edad_pac = edad_pac;
        this.sexo_pac = sexo_pac;
        this.id_doc = id_doc;
        this.password = password;
    }

    public Integer getUrgente() {
        return urgente;
    }



    public Integer getId_pac() {
        return id_pac;
    }



    public void setId_pac(Integer id_pac) {
        this.id_pac = id_pac;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public String getCedula_pac() {
        return cedula_pac;
    }

    public String getNombre_pac() {
        return nombre_pac;
    }

    public String getApellido_pac() {
        return apellido_pac;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getTelefono_pac() {
        return telefono_pac;
    }

    public double getNivelGlucosa() {
        return nivelGlucosa;
    }

    public void setNivelGlucosa(double nivelGlucosa) {
        this.nivelGlucosa = nivelGlucosa;
    }

    public int getEdad_pac() {
        return edad_pac;
    }

    public String getSexo_pac() {
        return sexo_pac;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
