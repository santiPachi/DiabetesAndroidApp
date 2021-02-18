package com.example.diabetes.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.diabetes.Adapters.AdaptadorDoctores;
import com.example.diabetes.Mediator.MediatorHistorial;
import com.example.diabetes.Modelo.Doctor;
import com.example.diabetes.R;

import java.util.ArrayList;

public class HistorialDoctores extends AppCompatActivity {
    Button btAnadirDoctor;
    ListView ltDoctores;


    MediatorHistorial mediatorHistorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_doctor);

        this.btAnadirDoctor= findViewById(R.id.bt_anadir_doctor);

        mediatorHistorial = new MediatorHistorial(this);
        mediatorHistorial.notificar("HistorialDoctores");

        this.btAnadirDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(),FormularioDoctor.class);
//                startActivity(intent);
            }
        });
    }



    public MediatorHistorial getMediatorHistorial() {
        return mediatorHistorial;
    }

    public void setMediatorHistorial(MediatorHistorial mediatorHistorial) {
        this.mediatorHistorial = mediatorHistorial;
    }

    public void setListaDoctores(ArrayList<Doctor> listaDoctores) {


        this.ltDoctores = findViewById(R.id.list_doctores);
        this.ltDoctores.setAdapter(new AdaptadorDoctores(this,listaDoctores));

        this.ltDoctores.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(android.widget.AdapterView<?> parent,
                                    View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), BitacoraPaciente.class);
                //To pass:
                startActivity(intent);

            }
        });
    }
}
