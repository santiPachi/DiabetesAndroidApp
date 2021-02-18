package com.example.diabetes.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.diabetes.Adapters.AdaptadorGlicemia;
import com.example.diabetes.Mediator.MediatorHistorial;
import com.example.diabetes.Modelo.Glicemia;
import com.example.diabetes.R;

import java.util.ArrayList;

public class HistorialGlicemia extends AppCompatActivity {
    ListView listaHistorial;
    ArrayList<Glicemia> listaGlicemias;

    MediatorHistorial mediatorHistorial;

    String[][] datos = {{"70","Bajo","2019/09/01","10:00"},
                        {"60","Bajo","2019/09/01","19:00"},
                        {"80","Medio","2019/09/02","10:00"},
                        {"73","Bajo","2019/09/02","19:00"},
                        {"102","Alto","2019/09/03","10:00"}};

    public HistorialGlicemia(MediatorHistorial mediatorHistorial) {
        this.mediatorHistorial = mediatorHistorial;
    }

    public HistorialGlicemia() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_glicemia);
        this.listaGlicemias = (ArrayList<Glicemia>) getIntent().getSerializableExtra("listaGlicemias");

        listaHistorial = findViewById(R.id.list_historial);
        this.mediatorHistorial = new MediatorHistorial(this);
        listaHistorial.setAdapter(new AdaptadorGlicemia(this,this.listaGlicemias));
    }

    public ArrayList<Glicemia> getListaGlicemias() {
        return listaGlicemias;
    }

    public void setListaGlicemias(ArrayList<Glicemia> listaGlicemias) {
        this.listaGlicemias = listaGlicemias;


    }
}
