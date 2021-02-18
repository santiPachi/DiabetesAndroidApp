package com.example.diabetes.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.diabetes.Mediator.MediatorAlta;
import com.example.diabetes.R;

public class FormularioDosis extends AppCompatActivity {
    public EditText etNph;
    public EditText etRapida;
    Button btGuardar;
    MediatorAlta  mediatorAlta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_dosis);
        mediatorAlta = new MediatorAlta(this);
        etNph = findViewById(R.id.et_nph_dosis);
        etRapida = findViewById(R.id.et_rapida_dosis);
        btGuardar = findViewById(R.id.bt_guardar_dosis);

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediatorAlta.notificar("GuardarDosis");
            }
        });
    }
}
