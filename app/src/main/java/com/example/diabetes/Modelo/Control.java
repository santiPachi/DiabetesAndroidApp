package com.example.diabetes.Modelo;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Control {
    @SerializedName("id_control")
    private int id_control;
    @SerializedName("id_pac")
    private int id_pac;
    @SerializedName("id_doc")
    private int id_doc;
    @SerializedName("fechaInicio")
    private String fechaInicio;
    @SerializedName("fechaRevision")
    private String fechaRevision;
    @SerializedName("estadoPaciente")
    private String estadoPaciente;
    @SerializedName("unidades")
    private double unidades;
    @SerializedName("decision")
    private String decision;
    @SerializedName("nphActual")
    private double nphActual;
    @SerializedName("rapidaActual")
    private double rapidaActual;
    @SerializedName("observaciones")
    private String observaciones;

    public Control(int id_control, int id_pac, int id_doc, String fechaInicio, String fechaRevision, String estadoPaciente, double unidades, String decision, double nphActual, double rapidaActual, String observaciones) {
        this.id_control = id_control;
        this.id_pac = id_pac;
        this.id_doc = id_doc;
        this.fechaInicio = fechaInicio;
        this.fechaRevision = fechaRevision;
        this.estadoPaciente = estadoPaciente;
        this.unidades = unidades;
        this.decision = decision;
        this.nphActual = nphActual;
        this.rapidaActual = rapidaActual;
        this.observaciones = observaciones;
    }

    public Control(int id_control, String estadoPaciente, String decision, double nphActual, double rapidaActual, String observaciones) {
        this.id_control = id_control;
        this.estadoPaciente = estadoPaciente;
        this.decision = decision;
        this.nphActual = nphActual;
        this.rapidaActual = rapidaActual;
        this.observaciones = observaciones;
    }

    public int getId_control() {
        return id_control;
    }

    public void setId_control(int id_control) {
        this.id_control = id_control;
    }

    public int getId_pac() {
        return id_pac;
    }

    public void setId_pac(int id_pac) {
        this.id_pac = id_pac;
    }

    public int getId_doc() {
        return id_doc;
    }

    public void setId_doc(int id_doc) {
        this.id_doc = id_doc;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaRevision() {
        return fechaRevision;
    }

    public void setFechaRevision(String fechaRevision) {
        this.fechaRevision = fechaRevision;
    }

    public String getEstadoPaciente() {
        return estadoPaciente;
    }

    public void setEstadoPaciente(String estadoPaciente) {
        this.estadoPaciente = estadoPaciente;
    }

    public double getUnidades() {
        return unidades;
    }

    public void setUnidades(double unidades) {
        this.unidades = unidades;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public double getNphActual() {
        return nphActual;
    }

    public void setNphActual(double nphActual) {
        this.nphActual = nphActual;
    }

    public double getRapidaActual() {
        return rapidaActual;
    }

    public void setRapidaActual(double rapidaActual) {
        this.rapidaActual = rapidaActual;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}

