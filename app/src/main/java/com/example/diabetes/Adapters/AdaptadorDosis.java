package com.example.diabetes.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.diabetes.Modelo.Dosis;
import com.example.diabetes.Modelo.Paciente;
import com.example.diabetes.R;

import java.util.ArrayList;

public class AdaptadorDosis extends ArrayAdapter<String> {
    private static LayoutInflater inflater = null;
    Context contexto;
    ArrayList<Dosis> datos;


    public AdaptadorDosis(Context contexto, ArrayList<Dosis> datos){
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
        final View vista = inflater.inflate(R.layout.elementro_dosis, null, true);
        TextView tvNph = vista.findViewById(R.id.tx_nph_dosis_ele);
        TextView tvRapida = vista.findViewById(R.id.tx_rapida_dosis_ele);
        tvNph.setText(String.valueOf(datos.get(position).getNph()));
        tvRapida.setText(String.valueOf(datos.get(position).getRapida()));
        return  vista;


    }
}
