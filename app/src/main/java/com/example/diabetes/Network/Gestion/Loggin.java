package com.example.diabetes.Network.Gestion;

import android.widget.Toast;

import com.example.diabetes.Mediator.MediatorLoggin;
import com.example.diabetes.Modelo.Paciente;
import com.example.diabetes.Modelo.Verify;
import com.example.diabetes.Network.Interface.LogginInterfaceApi;
import com.example.diabetes.Network.Interface.PacienteInterfaceApi;
import com.example.diabetes.Singleton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Loggin {
    MediatorLoggin mediatorLoggin;

    public Loggin(MediatorLoggin mediatorLoggin) {
        this.mediatorLoggin = mediatorLoggin;
    }

    public void verifyLoggin(String cedula, String Contrasenia) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Singleton.url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        LogginInterfaceApi logginInterfaceApi = retrofit.create(LogginInterfaceApi.class);

        Call<Verify> call = logginInterfaceApi.loggin(cedula, Contrasenia);
        final Verify[] verify = new Verify[1];
        call.enqueue(new Callback<Verify>() {
            @Override
            public void onResponse(Call<Verify> call, Response<Verify> response) {
                if(response.isSuccessful()){
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    verify[0] = response.body();
                    System.out.println("Fuck Yes"+ verify[0].getLoggueado());
                    mediatorLoggin.setVerify(verify[0]);


                    return;
                }



            }

            @Override
            public void onFailure(Call<Verify> call, Throwable t) {
                System.out.println("Erorrrr"+t.getMessage());

                Toast toast = Toast.makeText(mediatorLoggin.mainActivity.getApplicationContext(), "Cedula o Contraseña incorrectos", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }
}
