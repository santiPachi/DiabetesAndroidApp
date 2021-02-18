package com.example.diabetes.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.diabetes.Mediator.MediatorAlta;
import com.example.diabetes.R;

public class FormularioDoctor extends AppCompatActivity {
    public  Button btGuardar;
    public  EditText etNombre;
    public  EditText etApellido;
    public  EditText etCedula;
    public  EditText etTelefono;
    public  EditText etPass;
    MediatorAlta mediatorPerfil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_doctor);

        btGuardar = findViewById(R.id.bt_guardar_doctor);
        etApellido = findViewById(R.id.et_apellido_doctor);
        etCedula = findViewById(R.id.et_cedula_doctor);
        etTelefono = findViewById(R.id.et_telefono_doctor);
        etNombre = findViewById(R.id.et_nombre_doctor);
        etPass = findViewById(R.id.et_contraseia_doctor);

        mediatorPerfil = new MediatorAlta(this);
        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediatorPerfil.notificar("GuardarDoctor");

            }
        });
    }
}
