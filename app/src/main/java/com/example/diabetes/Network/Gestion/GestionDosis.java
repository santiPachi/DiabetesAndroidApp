package com.example.diabetes.Network.Gestion;

import android.content.Intent;
import android.widget.Toast;

import com.example.diabetes.Mediator.Mediator;
import com.example.diabetes.Mediator.MediatorAlta;
import com.example.diabetes.Mediator.MediatorHistorial;
import com.example.diabetes.Mediator.MediatorLoggin;

import com.example.diabetes.Modelo.Dosis;
import com.example.diabetes.Modelo.Paciente;
import com.example.diabetes.Network.Interface.DosisInterfaceApi;
import com.example.diabetes.Network.Interface.PacienteInterfaceApi;
import com.example.diabetes.Singleton;
import com.example.diabetes.View.Perfil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class GestionDosis {

    MediatorHistorial  mediatorHistorial;
    MediatorAlta mediatorAlta;
    MediatorLoggin mediatorLoggin;

    public GestionDosis(MediatorLoggin mediatorLoggin) {
        this.mediatorLoggin = mediatorLoggin;
    }

    public GestionDosis(MediatorAlta mediatorAlta) {
        this.mediatorAlta = mediatorAlta;
    }

    public GestionDosis(MediatorHistorial mediatorHistorial) {
        this.mediatorHistorial = mediatorHistorial;
    }

    public GestionDosis() {
    }

    public void setDosis(final Dosis dosis, int idPaciente){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Singleton.url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        DosisInterfaceApi dosisInterfaceApi = retrofit.create(DosisInterfaceApi.class);

        Call<ResponseBody> call = dosisInterfaceApi.setDosis(dosis.getNph(),dosis.getRapida(),idPaciente);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    System.out.println();
                    System.out.println();
                    System.out.println();

                    System.out.println("Dosis Guardado ");
                    Toast toast = Toast.makeText(mediatorAlta.formularioDosis.getApplicationContext(), "Dosis Guardada", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }



            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("Erorrrr"+t.getMessage());
                Toast toast = Toast.makeText(mediatorAlta.formularioDosis.getApplicationContext(), "Error al guardar dosis", Toast.LENGTH_SHORT);
                toast.show();
            }


        });

    }


    public void getDosisActual(int idPaciente) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Singleton.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DosisInterfaceApi dosisInterfaceApi = retrofit.create(DosisInterfaceApi.class);

        Call<Dosis> call = dosisInterfaceApi.getDosisActual(idPaciente);
        call.enqueue(new Callback<Dosis>() {
            @Override
            public void onResponse(Call<Dosis> call, Response<Dosis> response) {
                if(response.isSuccessful()){
                    System.out.println();
                    System.out.println();
                    System.out.println();

                    System.out.println("Dosis Actual"+ response.body());
                    Singleton.dosis = response.body();
                    GestionSemaforo gestionSemaforo = new GestionSemaforo(mediatorLoggin);
                    gestionSemaforo.getSemaforoAcutal(Singleton.paciente.getId_pac());

                    return;
                }



            }

            @Override
            public void onFailure(Call<Dosis> call, Throwable t) {
                System.out.println("Erorrrr"+t.getMessage());
            }


        });


    }

    public void getListaDosis(int idPaciente){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Singleton.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DosisInterfaceApi dosisInterfaceApi = retrofit.create(DosisInterfaceApi.class);

        Call<ArrayList<Dosis>> call = dosisInterfaceApi.getListaDosis(idPaciente);
        call.enqueue(new Callback<ArrayList<Dosis>>() {
            @Override
            public void onResponse(Call<ArrayList<Dosis>> call, Response<ArrayList<Dosis>> response) {
                if(response.isSuccessful()){
                    System.out.println();
                    System.out.println();
                    System.out.println();

                    System.out.println("lista dosis "+ response.body());
                    mediatorHistorial.historialDosis.setListaDosis(response.body());
                    return;
                }



            }

            @Override
            public void onFailure(Call<ArrayList<Dosis>> call, Throwable t) {
                System.out.println("Erorrrr"+t.getMessage());
            }


        });

    }
}
