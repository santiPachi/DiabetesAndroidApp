package com.example.diabetes.Mediator;

import android.content.Intent;

import com.example.diabetes.Modelo.Dosis;
import com.example.diabetes.Modelo.Glicemia;
import com.example.diabetes.Network.Gestion.GestionControl;
import com.example.diabetes.Network.Gestion.GestionDoctor;
import com.example.diabetes.Network.Gestion.GestionDosis;
import com.example.diabetes.Network.Gestion.GestionGlicemia;
import com.example.diabetes.Network.Gestion.GestionPaciente;
import com.example.diabetes.R;
import com.example.diabetes.Singleton;
import com.example.diabetes.View.GraficasActivity;
import com.example.diabetes.View.BitacoraPaciente;
import com.example.diabetes.View.HistorialDoctores;
import com.example.diabetes.View.HistorialDosis;
import com.example.diabetes.View.HistorialGlicemia;
import com.example.diabetes.View.HistorialPacientes;

import java.util.ArrayList;

public class MediatorHistorial implements Mediator {
    public HistorialDoctores historialDoctores;
    public HistorialPacientes historialPacientes;
    public BitacoraPaciente bitacoraPaciente;
    public HistorialGlicemia historialGlicemia;
    public HistorialDosis historialDosis;

    public MediatorHistorial(HistorialDosis historialDosis) {
        this.historialDosis = historialDosis;
    }

    public GraficasActivity graficasActivity;

    public MediatorHistorial(GraficasActivity graficasActivity) {
        this.graficasActivity = graficasActivity;
    }

    public MediatorHistorial(HistorialDoctores historialDoctores) {
        this.historialDoctores = historialDoctores;
    }

    public MediatorHistorial(HistorialPacientes historialPacientes) {
        this.historialPacientes = historialPacientes;
    }

    public MediatorHistorial(BitacoraPaciente bitacoraPaciente) {
        this.bitacoraPaciente = bitacoraPaciente;
    }

    public MediatorHistorial(HistorialGlicemia historialGlicemia) {
        this.historialGlicemia = historialGlicemia;
    }
    ArrayList<Glicemia> listaGlicemias;

    @Override
    public void notificar(String evento) {
        if (evento.equals( "GraficarControles")){
            GestionGlicemia gestionGlicemia = new GestionGlicemia(this);
            gestionGlicemia.getGlicemias(Singleton.idControl);

        }
        if (evento.equals( "HistorialPacientes")){
            GestionPaciente gestionPaciente = new GestionPaciente(this);
            gestionPaciente.getPacientes(Singleton.doctor.getCedula_doc());

        }
        if (evento.equals("HistorialDoctores")){

            GestionDoctor gestionDoctor = new GestionDoctor(this);
            gestionDoctor.getTodosDoctores();
        }

        if (evento.equals("HistorialBitacora")){
            GestionControl gestionControl = new GestionControl(this);
            gestionControl.getControles(Singleton.paciente.getId_pac());

        }
        if (evento.equals("HistorialGlicemias")){


            Intent intent = new Intent(this.graficasActivity.getApplicationContext(),HistorialGlicemia.class);
            intent.putExtra("listaGlicemias",this.listaGlicemias);
            this.graficasActivity.startActivity(intent);
        }if(evento.equals("HistorialDosis")){
            GestionDosis gestionDosis = new GestionDosis(this);
            gestionDosis.getListaDosis(Singleton.paciente.getId_pac());
        }

    }

    public ArrayList<Glicemia> getListaGlicemias() {
        return listaGlicemias;
    }

    public void setListaGlicemias(ArrayList<Glicemia> listaGlicemias) {
        this.listaGlicemias = listaGlicemias;

        this.graficasActivity.barCharGlicemiaAyuna = this.graficasActivity.setChartGlicemia(listaGlicemias,R.id.chart_glicemia_ayunas,"ayuno");
        this.graficasActivity.barCharGlicemiaDesayuno = this.graficasActivity.setChartGlicemia(listaGlicemias,R.id.chart_glicemia_desayuno,"desayuno");
        this.graficasActivity.barCharGlicemiaAlmuerzo =this.graficasActivity.setChartGlicemia(listaGlicemias,R.id.chart_glicemia_almuerzo,"almuerzo");
        this.graficasActivity.barCharGlicemiaMerienda =this.graficasActivity.setChartGlicemia(listaGlicemias,R.id.chart_glicemia_merienda,"merienda");
    }


}
