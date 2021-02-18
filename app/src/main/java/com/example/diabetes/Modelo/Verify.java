package com.example.diabetes.Modelo;

public class Verify {
    private String loggueado = "false";
    private String tipoUsuario;

    public Verify(String loggueado, String tipoUsuario) {
        this.loggueado = loggueado;
        this.tipoUsuario = tipoUsuario;
    }

    public Verify() {

    }

    public String getLoggueado() {
        return loggueado;
    }

    public void setLoggueado(String loggueado) {
        this.loggueado = loggueado;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}

