package com.example.diabetes.Modelo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Comida implements Serializable {
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("calorias")
    private String calorias;
    @SerializedName("acc")
    private String acc;
    @SerializedName("porcion")
    private String porcion;
    @SerializedName("unidad")
    private  String unidad;

    public Comida() {
    }

    public Comida(String nombre, String calorias, String acc, String porcion, String unidad) {
        this.nombre = nombre;
        this.calorias = calorias;
        this.acc = acc;
        this.porcion = porcion;
        this.unidad = unidad;
    }

    public String getPorcion() {
        return porcion;
    }

    public void setPorcion(String porcion) {
        this.porcion = porcion;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCalorias() {
        return calorias;
    }

    public void setCalorias(String calorias) {
        this.calorias = calorias;
    }

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }
}
