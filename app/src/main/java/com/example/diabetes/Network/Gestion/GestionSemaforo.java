package com.example.diabetes.Network.Gestion;

import android.widget.Toast;

import com.example.diabetes.Mediator.MediatorAlta;
import com.example.diabetes.Mediator.MediatorHistorial;
import com.example.diabetes.Mediator.MediatorLoggin;
import com.example.diabetes.Modelo.Paciente;
import com.example.diabetes.Modelo.Semaforo;
import com.example.diabetes.Network.Interface.PacienteInterfaceApi;
import com.example.diabetes.Network.Interface.SemaforoInterfaceApi;
import com.example.diabetes.Singleton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class GestionSemaforo {
    MediatorLoggin mediatorLoggin;
    MediatorAlta mediatorAlta;
    MediatorHistorial  mediatorHistorial;

    public GestionSemaforo(MediatorHistorial mediatorHistorial) {
        this.mediatorHistorial = mediatorHistorial;
    }

    public GestionSemaforo(MediatorLoggin mediatorLoggin) {
        this.mediatorLoggin = mediatorLoggin;
    }
    public GestionSemaforo(MediatorAlta mediatorAlta) {
        this.mediatorAlta = mediatorAlta;
    }

    public void getSemaforoAcutal(int idPac) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Singleton.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SemaforoInterfaceApi semaforoInterfaceApi = retrofit.create(SemaforoInterfaceApi.class);

        Call<Semaforo> call = semaforoInterfaceApi.getSemaforoActual(idPac);

        final Semaforo[] semaforo = new Semaforo[1];
        call.enqueue(new Callback<Semaforo>() {
            @Override
            public void onResponse(Call<Semaforo> call, Response<Semaforo> response) {
                if(response.body() != null){
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    semaforo[0]  = response.body();
                    System.out.println("Fuck Yes");
                    Singleton.semaforo = semaforo[0];
                    mediatorLoggin.notificar("PacienteCargado");
                    return;
                }


            }

            @Override
            public void onFailure(Call<Semaforo> call, Throwable t) {
                System.out.println("Erorrrr"+t.getMessage());
            }
        });

    }

}
