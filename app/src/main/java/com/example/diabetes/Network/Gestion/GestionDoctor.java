package com.example.diabetes.Network.Gestion;

import android.content.Intent;
import android.widget.Toast;

import com.example.diabetes.Mediator.MediatorHistorial;
import com.example.diabetes.Mediator.MediatorLoggin;
import com.example.diabetes.Mediator.MediatorAlta;
import com.example.diabetes.Modelo.Doctor;
import com.example.diabetes.Network.Interface.DoctorInterfaceApi;
import com.example.diabetes.View.PerfilDoctor;
import com.example.diabetes.Singleton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GestionDoctor {
    MediatorLoggin mediatorLoggin;
    MediatorAlta mediatorAlta;
    MediatorHistorial mediatorHistorial;

    public GestionDoctor(MediatorHistorial mediatorHistorial) {
        this.mediatorHistorial = mediatorHistorial;
    }

    public GestionDoctor(MediatorLoggin mediatorLoggin) {
        this.mediatorLoggin = mediatorLoggin;
    }

    public GestionDoctor(MediatorAlta mediatorPerfil) {
        this.mediatorAlta = mediatorPerfil;
    }
    public void guardarDoctor(Doctor doctor,String password){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Singleton.url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        DoctorInterfaceApi doctorInterfaceApi = retrofit.create(DoctorInterfaceApi.class);

        Call<Doctor> call = doctorInterfaceApi.guardarDoctor(doctor.getNombre_doc(),doctor.getTipoUsuario(),doctor.getCedula_doc(),doctor.getApellido_doc(),doctor.getNombreUsuario(),password,doctor.getTelefono_doc());
        call.enqueue(new Callback<Doctor>() {
            @Override
            public void onResponse(Call<Doctor> call, Response<Doctor> response) {
                if(response.isSuccessful()){
                    System.out.println();
                    System.out.println();
                    System.out.println();

                    System.out.println("Fuck Yes Doctor Guardado "+ response.body());
                    Toast toast = Toast.makeText(mediatorAlta.formularioDoctor.getApplicationContext(), "Doctor Guardado", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }



            }

            @Override
            public void onFailure(Call<Doctor> call, Throwable t) {
                System.out.println("Erorrrr"+t.getMessage());
                Toast toast = Toast.makeText(mediatorAlta.revisarControl.getApplicationContext(), "Error al guardar doctor", Toast.LENGTH_SHORT);
                toast.show();
            }


        });

    }

    public void getTodosDoctores() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Singleton.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DoctorInterfaceApi medicoInterfaceApi = retrofit.create(DoctorInterfaceApi.class);

        Call<ArrayList<Doctor>> call = medicoInterfaceApi.getTodosDoctores();


        call.enqueue(new Callback<ArrayList<Doctor>>() {
            @Override
            public void onResponse(Call<ArrayList<Doctor>> call, Response<ArrayList<Doctor>> response) {
                if(response.body() != null){
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    ArrayList<Doctor> doctores  = response.body();

                    mediatorHistorial.historialDoctores.setListaDoctores(doctores);

                    return;
                }

            }

            @Override
            public void onFailure(Call<ArrayList<Doctor>> call, Throwable t) {
                System.out.println("Erorrrr"+t.getMessage());
            }
        });
    }
    public void getDoctor(String cedula) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Singleton.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DoctorInterfaceApi medicoInterfaceApi = retrofit.create(DoctorInterfaceApi.class);

        Call<Doctor> call = medicoInterfaceApi.getMedico(cedula);

        final Doctor[] doctor = new Doctor[1];
        call.enqueue(new Callback<Doctor>() {
            @Override
            public void onResponse(Call<Doctor> call, Response<Doctor> response) {
                if(response.body() != null){
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    doctor[0]  = response.body();
                    Singleton.doctor = doctor[0];
                    System.out.println("Fuck Yes"+ doctor[0].getNombre_doc());
                    Intent intent = new Intent(mediatorLoggin.mainActivity, PerfilDoctor.class);
                    intent.putExtra("doctor", doctor[0]);
                    mediatorLoggin.mainActivity.startActivity(intent);

                    return;
                }

            }

            @Override
            public void onFailure(Call<Doctor> call, Throwable t) {
                System.out.println("Erorrrr"+t.getMessage());
            }
        });
    }


}
