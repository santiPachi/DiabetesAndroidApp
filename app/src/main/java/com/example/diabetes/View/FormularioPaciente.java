package com.example.diabetes.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.diabetes.Mediator.MediatorAlta;
import com.example.diabetes.R;
public class FormularioPaciente extends AppCompatActivity {
    Button btGuardar;
    EditText etNombre;
    EditText etApellido;
    EditText etCedula;
    EditText etEdad;
    EditText etTelefono;
    EditText etContrasenia;
    MediatorAlta mediatorPerfil;
    Spinner spinnerSexo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_paciente);

        btGuardar = findViewById(R.id.bt_guardar_paciente);
        etApellido = findViewById(R.id.et_apellido_paciente);
        etCedula = findViewById(R.id.et_cedula_paciente);
        etEdad = findViewById(R.id.et_edad_paciente);
        etTelefono = findViewById(R.id.et_telefono_paciente);
        etNombre = findViewById(R.id.et_nombre_paciente);
        etContrasenia = findViewById(R.id.et_contraseia_paciente);
        mediatorPerfil = new MediatorAlta(this);
        spinnerSexo = findViewById(R.id.spinner_sexo);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sexo_array, R.layout.spinner_sexo);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerSexo.setAdapter(adapter);



        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediatorPerfil.notificar("GuardarPaciente");
//                Intent intent = new Intent(getApplicationContext(), FormularioDosis.class);
//                startActivity(intent);
            }
        });


    }

    public EditText getEtEdad() {
        return etEdad;
    }

    public void setEtEdad(EditText etEdad) {
        this.etEdad = etEdad;
    }

    public EditText getEtContrasenia() {
        return etContrasenia;
    }

    public void setEtContrasenia(EditText etContrasenia) {
        this.etContrasenia = etContrasenia;
    }

    public Button getBtGuardar() {
        return btGuardar;
    }

    public void setBtGuardar(Button btGuardar) {
        this.btGuardar = btGuardar;
    }

    public EditText getEtNombre() {
        return etNombre;
    }

    public void setEtNombre(EditText etNombre) {
        this.etNombre = etNombre;
    }

    public EditText getEtApellido() {
        return etApellido;
    }

    public void setEtApellido(EditText etApellido) {
        this.etApellido = etApellido;
    }

    public EditText getEtCedula() {
        return etCedula;
    }

    public void setEtCedula(EditText etCedula) {
        this.etCedula = etCedula;
    }

    public EditText getEtTelefono() {
        return etTelefono;
    }

    public void setEtTelefono(EditText etTelefono) {
        this.etTelefono = etTelefono;
    }

    public MediatorAlta getMediatorPerfil() {
        return mediatorPerfil;
    }

    public void setMediatorPerfil(MediatorAlta mediatorPerfil) {
        this.mediatorPerfil = mediatorPerfil;
    }

    public Spinner getSpinnerSexo() {
        return spinnerSexo;
    }

    public void setSpinnerSexo(Spinner spinnerSexo) {
        this.spinnerSexo = spinnerSexo;
    }
}
