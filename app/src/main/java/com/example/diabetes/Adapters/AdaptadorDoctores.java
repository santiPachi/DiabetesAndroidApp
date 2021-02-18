package com.example.diabetes.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.diabetes.Modelo.Doctor;
import com.example.diabetes.Modelo.Paciente;
import com.example.diabetes.R;

import java.util.ArrayList;

public class AdaptadorDoctores extends ArrayAdapter<String> {
    private static LayoutInflater inflater = null;
    Context contexto;
    ArrayList<Doctor> datos;



    public AdaptadorDoctores(Context contexto, ArrayList<Doctor> datos){
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

        final View vista = inflater.inflate(R.layout.elemento_doctor, null,true);

        TextView tvNombre = vista.findViewById(R.id.tx_nombre_doctor);

        TextView tvTelefono = vista.findViewById(R.id.tx_telefono_doctor);



        tvNombre.setText(this.datos.get(position).getNombre_doc()+" "+this.datos.get(position).getApellido_doc());


        tvTelefono.setText(String.valueOf(this.datos.get(position).getTelefono_doc()));


        return vista;
    }



}
