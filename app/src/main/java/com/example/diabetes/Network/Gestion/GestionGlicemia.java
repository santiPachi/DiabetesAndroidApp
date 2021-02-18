package com.example.diabetes.Network.Gestion;

import android.widget.Toast;

import com.example.diabetes.Mediator.MediatorAlta;
import com.example.diabetes.Mediator.MediatorHistorial;
import com.example.diabetes.Modelo.Glicemia;
import com.example.diabetes.Modelo.Semaforo;
import com.example.diabetes.Network.Interface.GlicemiaInterfaceApi;
import com.example.diabetes.Singleton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GestionGlicemia {
    MediatorHistorial mediatorHistorial;
    MediatorAlta mediatorAlta;

    public GestionGlicemia(MediatorAlta mediatorAlta) {
        this.mediatorAlta = mediatorAlta;
    }

    public GestionGlicemia() {
    }

    public GestionGlicemia(MediatorHistorial mediatorHistorial) {
        this.mediatorHistorial = mediatorHistorial;
    }

    public void getGlicemias(int idControl){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Singleton.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GlicemiaInterfaceApi glicemiaInterfaceApi = retrofit.create(GlicemiaInterfaceApi.class);

        Call<ArrayList<Glicemia>> call = glicemiaInterfaceApi.getGlicemias(idControl);


        call.enqueue(new Callback<ArrayList<Glicemia>>() {
            @Override
            public void onResponse(Call<ArrayList<Glicemia>> call, Response<ArrayList<Glicemia>> response) {
                if(response.body() != null){
                    System.out.println();
                    System.out.println();
                    System.out.println();

                    System.out.println("Fuck Yes");

                    mediatorHistorial.setListaGlicemias(response.body());

                }
                System.out.println("Fuck no");

            }

            @Override
            public void onFailure(Call<ArrayList<Glicemia>> call, Throwable t) {
                System.out.println("Erorrrr"+t.getMessage());
            }


        });
    }
    public void guardarGlicemia(int nivel, int opcion){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Singleton.url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        GlicemiaInterfaceApi glicemiaInterfaceApi = retrofit.create(GlicemiaInterfaceApi.class);
        Glicemia glicemia = new Glicemia("ninguna");
        glicemia.setNivel(nivel,opcion);
        Call<Semaforo> call = glicemiaInterfaceApi.setGlicemia(glicemia.getFecha(),glicemia.getNivelGlucosa(),glicemia.getAyunas(),glicemia.getDesayuno(),glicemia.getAlmuerzo(),glicemia.getMerienda(),glicemia.getObservaciones(),Singleton.paciente.getId_pac());
        final Glicemia[] glicemias = new Glicemia[1];
            call.enqueue(new Callback<Semaforo>() {
            @Override
            public void onResponse(Call<Semaforo> call, Response<Semaforo> response) {
                if(response.isSuccessful()){
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    Semaforo semaforo = response.body();
                    Singleton.semaforo = semaforo;
                    System.out.println("Fuck Yes Glicemia "+ semaforo.getColor());
                    Toast toast = Toast.makeText(mediatorAlta.formularioGlicemia.getApplicationContext(), "Glicemia Guardada", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }



            }

                @Override
                public void onFailure(Call<Semaforo> call, Throwable t) {
                    System.out.println("Erorrrr"+t.getMessage());
                    Toast toast = Toast.makeText(mediatorAlta.formularioGlicemia.getApplicationContext(), "Error al guardad Glicemia", Toast.LENGTH_SHORT);
                    toast.show();
                }


        });

    }
}
