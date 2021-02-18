package com.example.diabetes.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.diabetes.Modelo.Glicemia;
import com.example.diabetes.R;

import java.util.ArrayList;

public class AdaptadorGlicemia extends ArrayAdapter<String> {
    private static LayoutInflater inflater = null;
    Context contexto;
    ArrayList<Glicemia> datos;



    public AdaptadorGlicemia(Context contexto, ArrayList<Glicemia> datos){
        super(contexto, R.layout.elemento_glicemia);
        this.contexto = contexto;
        this.datos = datos;
        inflater = LayoutInflater.from(this.contexto);
    }

    @Override
    public int getCount() {
        return datos.size();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final View vista = inflater.inflate(R.layout.elemento_glicemia, null,true);

        TextView tvGlucosa = vista.findViewById(R.id.tx_glucosa);
        TextView tvTiempo = vista.findViewById(R.id.tx_nivel);
        TextView tvHora = vista.findViewById(R.id.tx_hora);
        TextView tvFecha = vista.findViewById(R.id.tx_fecha);

        tvGlucosa.setText(String.valueOf(this.datos.get(position).getNivelGlucosa()));
        String tiempo  = "";
        if( this.datos.get(position).getAlmuerzo() ==1){
            tiempo = "Almuerzo";
        }else if(this.datos.get(position).getAyunas()==1){
            tiempo = "Ayunas";
        }else if(this.datos.get(position).getDesayuno()==1){
            tiempo = "Desayuno";
        }else{
            tiempo = "Merienda";
        }
        tvTiempo.setText(tiempo);
        String fecha = this.datos.get(position).getFecha().substring(0,10);
        String hora = this.datos.get(position).getFecha().substring(11,16);
        tvFecha.setText(fecha);
        tvHora.setText(hora);

        return vista;
    }



}
