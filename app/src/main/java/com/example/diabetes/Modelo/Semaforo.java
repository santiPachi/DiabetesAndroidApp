package com.example.diabetes.Modelo;

public class Semaforo  {
    String color;
    String urgente;

    public Semaforo(String color, String urgente) {
        this.color = color;
        this.urgente = urgente;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getUrgente() {
        return urgente;
    }

    public void setUrgente(String urgente) {
        this.urgente = urgente;
    }
}
