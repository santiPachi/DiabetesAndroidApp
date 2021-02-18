package com.example.diabetes.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.diabetes.R;
import com.example.diabetes.Singleton;

public class PerfilAdmin extends AppCompatActivity {
    Button btMedicos;
    Button btPacientes;
    Button btAjustes;
    TextView tvNombre;
    TextView tvApellido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_admin);

        btAjustes = findViewById(R.id.bt_ajustes_medico);
        btMedicos = findViewById(R.id.bt_medicos_admin);
        btPacientes = findViewById(R.id.bt_pacientes_admin);
        tvNombre = findViewById(R.id.tx_nombre_admin);
        tvApellido = findViewById(R.id.tx_apellido_admin);


        tvNombre.setText(Singleton.admin.getNombre_adm());
        tvApellido.setText(Singleton.admin.getApellido_adm());

        btPacientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), HistorialPacientes.class);
                startActivity(intent);
            }
        });

        btMedicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), HistorialDoctores.class);
                startActivity(intent);
            }
        });
    }
}
