package com.example.diabetes.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.diabetes.Mediator.Mediator;
import com.example.diabetes.Mediator.MediatorAlta;
import com.example.diabetes.R;

public class RevisarControl extends AppCompatActivity {
    Button btGuardar;
    public EditText etNph;
    public EditText etRapida;

    public EditText etObservacoines;
    public Spinner spEstado;
    MediatorAlta mediatorAlta;
    @Override
    public int getChangingConfigurations() {
        return super.getChangingConfigurations();
    }

    public Spinner spDecision;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revisar_control);
        mediatorAlta = new MediatorAlta(this);
        etNph = findViewById(R.id.et_nph_control);
        etObservacoines = findViewById(R.id.et_observaciones);
        etRapida = findViewById(R.id.et_rapida_control);
        spDecision = findViewById(R.id.spinner_decision);
        btGuardar = findViewById(R.id.bt_guardar_revision);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.decision_array, R.layout.spinner_sexo);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spDecision.setAdapter(adapter);

        spEstado = findViewById(R.id.spinner_estado);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.estado_array, R.layout.spinner_sexo);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spEstado.setAdapter(adapter2);

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediatorAlta.notificar("GuardarRevisionControl");
            }
        });
    }
}
