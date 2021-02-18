package com.example.diabetes.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.diabetes.Adapters.AdaptadorControl;
import com.example.diabetes.Mediator.MediatorHistorial;
import com.example.diabetes.Modelo.Accion;
import com.example.diabetes.Modelo.Control;
import com.example.diabetes.Modelo.EstadoControl;
import com.example.diabetes.R;
import com.example.diabetes.Singleton;

import java.util.ArrayList;

public class BitacoraPaciente extends AppCompatActivity {
    ListView listControl;
    ArrayList<Control> listaControles;
    MediatorHistorial mediatorHistorial;
    TextView tvNombre;
    TextView tvEdad;
    TextView tvSexo;
    TextView tvTelefono;
    TextView tvCedula;
    TextView tvGlucosa;
    Button btHistorialDosis;
    String[] datosAnalisis = {"Dr. Diego Bustamante", "Los niveles se mantienen estables", "03/09/2019", Accion.Conservar.toString(), "0.1"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitacora_paciente);

        tvCedula = findViewById(R.id.tx_cedula_paciente_info);
        tvEdad = findViewById(R.id.tx_edad_paciente_info);
        tvGlucosa = findViewById(R.id.tx_glucosa_paciente_info);
        tvNombre = findViewById(R.id.tx_nombre_paciente_info);
        tvSexo = findViewById(R.id.tx_sexo_paciente_info);
        tvTelefono = findViewById(R.id.tx_telefono_paciente_info);
        btHistorialDosis = findViewById(R.id.bt_historial_dosis);
        tvTelefono.setText(Singleton.paciente.getTelefono_pac());
        tvSexo.setText(Singleton.paciente.getSexo_pac());
        tvNombre.setText(Singleton.paciente.getNombre_pac());
        tvGlucosa.setText(String.valueOf(Singleton.paciente.getNivelGlucosa()));
        tvEdad.setText(String.valueOf(Singleton.paciente.getEdad_pac()));
        tvCedula.setText(Singleton.paciente.getCedula_pac());

        mediatorHistorial = new MediatorHistorial(this);
        mediatorHistorial.notificar("HistorialBitacora");

        btHistorialDosis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),HistorialDosis.class);
                startActivity(intent );
            }
        });
    }

    public ArrayList<Control> getListaControles() {
        return listaControles;
    }

    public void setListaControles(final ArrayList<Control> listaControles) {
        this.listaControles = listaControles;


        listControl = findViewById(R.id.list_historial_control);

        listControl.setAdapter(new AdaptadorControl(this,this.listaControles));

        listControl.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(android.widget.AdapterView<?> parent,
                                    View view, int position, long id) {
                Singleton.idControl = listaControles.get(position).getId_control();
                Intent intent = new Intent(view.getContext(), GraficasActivity.class);
                //To pass:
                intent.putExtra("Doctor", datosAnalisis[0]);
                intent.putExtra("Descripcion", datosAnalisis[1]);
                intent.putExtra("Fecha", datosAnalisis[2]);
                intent.putExtra("Accion", datosAnalisis[3]);
                intent.putExtra("Variacion", datosAnalisis[4]);

                startActivity(intent);

            }
        });
    }
}
