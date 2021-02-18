package com.example.diabetes.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.diabetes.R;
import com.example.diabetes.Singleton;

public class PerfilDoctor extends AppCompatActivity {
    Button btUrgentes;
    Button btPacientes;
    Button btAjustes;
    TextView tvNombre;
    TextView tvApellido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_doctor);


        btAjustes = findViewById(R.id.bt_ajustes_medico);
        btUrgentes = findViewById(R.id.bt_urgentes_medico);
        btPacientes = findViewById(R.id.bt_pacientes_medico);
        tvNombre = findViewById(R.id.tx_nombre_medico);
        tvApellido = findViewById(R.id.tx_apellido_medico);
        tvNombre.setText(Singleton.doctor.getNombre_doc());
        tvApellido.setText(Singleton.doctor.getApellido_doc());
        btPacientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), HistorialPacientes.class);
                intent.putExtra("urgente",false);
                startActivity(intent);
            }
        });

        btUrgentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), HistorialPacientes.class);
                intent.putExtra("urgente",true);
                startActivity(intent);
            }
        });
    }
}
