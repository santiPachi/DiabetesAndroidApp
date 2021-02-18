package com.example.diabetes.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.diabetes.Adapters.AdaptadorPacientes;
import com.example.diabetes.Mediator.MediatorHistorial;
import com.example.diabetes.Modelo.Paciente;
import com.example.diabetes.R;
import com.example.diabetes.Singleton;

import java.util.ArrayList;

public class HistorialPacientes extends AppCompatActivity {
    ListView listViewPacientes;

    Button btAnadirPaciente;
    MediatorHistorial mediatorHistorial;

    public HistorialPacientes(MediatorHistorial mediatorHistorial) {
        this.mediatorHistorial = mediatorHistorial;
    }

    public HistorialPacientes() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacientes);

        this.btAnadirPaciente = findViewById(R.id.bt_anadir_paciente);
        this.mediatorHistorial = new MediatorHistorial(this);

        this.mediatorHistorial.notificar("HistorialPacientes");
//
//        this.listaPacientes = new ArrayList<Paciente>();
//        this.listaPacientes.add(new Paciente(1,"Juan","3","212323423",0,"Polo","polopo","2323232",40,30,"M",1));
//        this.listaPacientes.add(new Paciente(2,"Alex","3","212323423",1,"Polo","polopo","2323232",80,30,"M",1));
//        this.listaPacientes.add(new Paciente(3,"Pancho","3","212323423",0
//                ,"Polo","polopo","2323232",30,30,"M",1));
//        this.listaPacientes.add(new Paciente(4,"Eleias","3","212323423",1,"Polo","polopo","2323232",20,30,"M",1));
//        this.listaPacientes.add(new Paciente(5,"Marco","3","212323423",1,"Polo","polopo","2323232",20,30,"M",1));



        this.btAnadirPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPaciente = new Intent(v.getContext(),FormularioPaciente.class);
                startActivity(intentPaciente);
            }
        });
    }



    public void setListaPacientes(final ArrayList<Paciente> listaPacientes){

        this.listViewPacientes = findViewById(R.id.list_pacientes);
        Boolean urgente =(Boolean) getIntent().getBooleanExtra("urgente",false);
        this.listViewPacientes.setAdapter(new AdaptadorPacientes(this,listaPacientes,urgente));

        this.listViewPacientes.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(android.widget.AdapterView<?> parent,
                View view, int position, long id) {
            Singleton.paciente = listaPacientes.get(position);
            Intent intent = new Intent(view.getContext(), BitacoraPaciente.class);
            //To pass:
            startActivity(intent);

        }
    });

}
}
