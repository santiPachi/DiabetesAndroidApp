package com.example.diabetes.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.diabetes.Modelo.Paciente;
import com.example.diabetes.R;

import java.util.ArrayList;

public class AdaptadorPacientes extends ArrayAdapter<String> {
    private static LayoutInflater inflater = null;
    Context contexto;
    ArrayList<Paciente> datos;
    private Boolean urgente;


    public AdaptadorPacientes(Context contexto, ArrayList<Paciente> datos,Boolean urgente){
        super(contexto, R.layout.elemento_glicemia);
        this.contexto = contexto;
        this.urgente = urgente;
        this.datos = datos;
        inflater = LayoutInflater.from(this.contexto);
    }

    @Override
    public int getCount() {
        return datos.size();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View vista = inflater.inflate(R.layout.elemento_paciente, null, true);
        TextView tvNombre = vista.findViewById(R.id.tx_nombre_paciente);
        TextView tvEdad = vista.findViewById(R.id.tx_edad_paciente);
//        TextView tvSexo = vista.findViewById(R.id.tx_sexo_paciente);
        TextView tvNivel = vista.findViewById(R.id.tx_nivel_paciente);
        ImageView imgUrgencia = vista.findViewById(R.id.img_urgencia_paciente);
        if (!this.urgente || this.datos.get(position).getUrgente() == 1) {





            tvNombre.setText(this.datos.get(position).getNombre_pac() + " " + this.datos.get(position).getApellido_pac());
            tvNivel.setText(String.valueOf(this.datos.get(position).getNivelGlucosa()));
//        tvSexo.setText("Sexo: "+this.datos.get(position).getSexo());
            tvEdad.setText(String.valueOf(this.datos.get(position).getEdad_pac()));

            if (this.datos.get(position).getUrgente() == 1) {
                imgUrgencia.setImageResource(0);
                imgUrgencia.setImageResource(R.drawable.ic_urgencia_true);
            } else {
                if (!this.urgente) {
                    imgUrgencia.setImageResource(0);
                    imgUrgencia.setImageResource(R.drawable.ic_urgencia_false);
                }

            }

        }else{
            vista.setVisibility(View.INVISIBLE);
        }

        return vista;
    }



}
