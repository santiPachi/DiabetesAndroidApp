package com.example.diabetes.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.diabetes.Modelo.Control;
import com.example.diabetes.R;

import java.util.ArrayList;

public class AdaptadorControl extends ArrayAdapter<String> {
    private static LayoutInflater inflater = null;
    Context contexto;
    ArrayList<Control> datos;

    public AdaptadorControl(Context contexto, ArrayList<Control> datos){
        super(contexto, R.layout.elementro_control);
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

        final View vista = inflater.inflate(R.layout.elementro_control, null,true);

        TextView tvEstado = vista.findViewById(R.id.tx_ele_control_estado);
        TextView tvFechaIni = vista.findViewById(R.id.tx_ele_contro_fecha_ini);
        TextView tvFechaFin = vista.findViewById(R.id.tx_ele_contro_fecha_fin);
        if(this.datos.get(position).getEstadoPaciente() != null)
            tvEstado.setText(this.datos.get(position).getEstadoPaciente());
        if(this.datos.get(position).getFechaInicio()!=null) {
            String fechaini = this.datos.get(position).getFechaInicio().substring(0, 10);
            tvFechaIni.setText(fechaini);
        }
        if (this.datos.get(position).getFechaRevision()!=null) {
            String fechafin = this.datos.get(position).getFechaRevision().substring(0, 10);
            tvFechaFin.setText(fechafin);
        }
        return vista;
    }



}
