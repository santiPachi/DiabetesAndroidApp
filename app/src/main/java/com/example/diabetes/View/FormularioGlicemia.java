package com.example.diabetes.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.diabetes.Mediator.MediatorAlta;
import com.example.diabetes.Network.Gestion.GestionGlicemia;
import com.example.diabetes.R;

public class FormularioGlicemia extends AppCompatActivity {
    Button btGuardar;
    public EditText etNivelGlucosa;
    public Spinner spinnerHorario;
    MediatorAlta mediatorAlta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_glicemia);
        mediatorAlta = new MediatorAlta(this);
        btGuardar = findViewById(R.id.bt_guardar_glicemia);
        etNivelGlucosa = findViewById(R.id.et_nivel_glicemia);
        spinnerHorario = findViewById(R.id.spinner_horario);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.horario_array, R.layout.spinner_sexo);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerHorario.setAdapter(adapter);

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediatorAlta.notificar("GuardarGlicemia");

            }
        });
    }
}
