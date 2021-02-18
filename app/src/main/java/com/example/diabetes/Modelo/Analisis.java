package com.example.diabetes.Modelo;

import java.util.Date;

public class Analisis {
    private int id;
    private int idMedico;
    private int idPaciente;
    private Date fecha;
    private String descripcion;
    private Accion accion;
    private float variacion;

    public Analisis(int id, int idMedico, int idPaciente, Date fecha, String descripcion, Accion accion, float variacion) {
        this.id = id;
        this.idMedico = idMedico;
        this.idPaciente = idPaciente;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.accion = accion;
        this.variacion = variacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Accion getAccion() {
        return accion;
    }

    public void setAccion(Accion accion) {
        this.accion = accion;
    }

    public float getVariacion() {
        return variacion;
    }

    public void setVariacion(float variacion) {
        this.variacion = variacion;
    }
}

