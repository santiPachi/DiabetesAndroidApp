package com.example.diabetes.Network.Gestion;

import android.content.Intent;

import com.example.diabetes.Mediator.MediatorLoggin;
import com.example.diabetes.Modelo.Admin;
import com.example.diabetes.Network.Interface.AdminInterfaceApi;
import com.example.diabetes.Singleton;
import com.example.diabetes.View.PerfilAdmin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GestionAdmin {
    MediatorLoggin mediatorLoggin;
    public GestionAdmin(MediatorLoggin mediatorLoggin) {
        this.mediatorLoggin = mediatorLoggin;
    }




    public void getAdmin(String cedula) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Singleton.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AdminInterfaceApi adminInterfaceApi = retrofit.create(AdminInterfaceApi.class);

        Call<Admin> call = adminInterfaceApi.getAdmin(cedula);

        final Admin[] admins = new Admin[1];
        call.enqueue(new Callback<Admin>() {
            @Override
            public void onResponse(Call<Admin> call, Response<Admin> response) {
                if(response.body() != null){
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    admins[0]  = response.body();
                    Singleton.admin = admins[0];
                    System.out.println("Fuck Yes admin"+ admins[0].getNombre_adm());
                    Intent intent = new Intent(mediatorLoggin.mainActivity, PerfilAdmin.class);
                    mediatorLoggin.mainActivity.startActivity(intent);

                    return;
                }

            }

            @Override
            public void onFailure(Call<Admin> call, Throwable t) {
                System.out.println("Erorrrr"+t.getMessage());
            }


        });
    }
}
