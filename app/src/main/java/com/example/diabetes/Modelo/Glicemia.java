package com.example.diabetes.Modelo;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
public class Glicemia implements Serializable {
    @SerializedName("id_glicemia")
    private int id_id_glicemia;
    @SerializedName("nivelGlucosa")
    private int nivelGlucosa;
    @SerializedName("ayunas")
    private int ayunas;
    @SerializedName("desayuno")
    private int desayuno;
    @SerializedName("almuerzo")
    private int almuerzo;
    @SerializedName("merienda")
    private int merienda;
    @SerializedName("id_control")
    private int id_control;
    @SerializedName("observaciones")
    private String observaciones;
    @SerializedName("fecha")
    private String fecha;

    public Glicemia( String observaciones) {
        this.observaciones = observaciones;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.fecha = dtf.format(now);
        this.nivelGlucosa = 0;
        this.ayunas =0;
        this.almuerzo = 0;
        this.desayuno = 0;
        this.merienda = 0;

    }

    public int getNivelGlucosa() {
        return nivelGlucosa;
    }

    public void setNivelGlucosa(int nivelGlucosa) {
        this.nivelGlucosa = nivelGlucosa;
    }
    public void setNivel(int nivel,int opcion){
        this.nivelGlucosa = nivel;
        switch (opcion){
            case 0: this.ayunas = 1; break;
            case 1: this.desayuno = 1; break;
            case 2: this.almuerzo = 1; break;
            case 3: this.merienda = 1; break;
        }
    }

    public int getAlmuerzo() {
        return almuerzo;
    }

    public void setAlmuerzo(int almuerzo) {
        this.almuerzo = almuerzo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getAyunas() {
        return ayunas;
    }

    public void setAyunas(int ayunas) {
        this.ayunas = ayunas;
    }

    public int getDesayuno() {
        return desayuno;
    }

    public void setDesayuno(int desayuno) {
        this.desayuno = desayuno;
    }

    public int getMerienda() {
        return merienda;
    }

    public void setMerienda(int merienda) {
        this.merienda = merienda;
    }

    public int getId_control() {
        return id_control;
    }

    public void setId_control(int id_control) {
        this.id_control = id_control;
    }
}
