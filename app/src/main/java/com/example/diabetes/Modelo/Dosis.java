package com.example.diabetes.Modelo;

import java.io.Serializable;
import java.util.List;

public class Dosis implements Serializable {
    private double nph;
    private double rapida;

    public Dosis(double nph, double rapida) {
        this.nph = nph;
        this.rapida = rapida;
    }

    public double getNph() {
        return nph;
    }

    public void setNph(double nph) {
        this.nph = nph;
    }

    public double getRapida() {
        return rapida;
    }

    public void setRapida(double rapida) {
        this.rapida = rapida;
    }
}
