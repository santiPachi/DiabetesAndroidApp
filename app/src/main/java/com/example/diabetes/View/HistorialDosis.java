package com.example.diabetes.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.diabetes.Adapters.AdaptadorDosis;
import com.example.diabetes.Mediator.MediatorHistorial;
import com.example.diabetes.Modelo.Dosis;
import com.example.diabetes.R;

import java.util.ArrayList;

public class HistorialDosis extends AppCompatActivity {
    ListView listViewDosis;
    ArrayList<Dosis> listaDosis;
    MediatorHistorial mediatorHistorial;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_dosis);
        this.listaDosis = new ArrayList<Dosis>();
        this.mediatorHistorial = new MediatorHistorial(this);
        this.mediatorHistorial.notificar("HistorialDosis");

    }



    public void setListaDosis(ArrayList<Dosis> listaDosis){
        this.listaDosis = listaDosis;
        this.listViewDosis = findViewById(R.id.list_dosis);
        this.listViewDosis.setAdapter(new AdaptadorDosis(this,this.listaDosis));

        this.listViewDosis.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
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
