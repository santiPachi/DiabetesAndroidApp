package com.example.diabetes.Network.Gestion;

import android.content.Intent;
import android.widget.Toast;

import com.example.diabetes.Mediator.MediatorHistorial;
import com.example.diabetes.Mediator.MediatorLoggin;
import com.example.diabetes.Mediator.MediatorAlta;
import com.example.diabetes.Modelo.Paciente;
import com.example.diabetes.Network.Interface.PacienteInterfaceApi;
import com.example.diabetes.View.Perfil;
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


public class GestionPaciente   {
    MediatorLoggin mediatorLoggin;
    MediatorAlta mediatorAlta;
    MediatorHistorial  mediatorHistorial;

    public GestionPaciente(MediatorHistorial mediatorHistorial) {
        this.mediatorHistorial = mediatorHistorial;
    }

    public GestionPaciente(MediatorLoggin mediatorLoggin) {
        this.mediatorLoggin = mediatorLoggin;
    }
    public GestionPaciente(MediatorAlta mediatorPerfil) {
        this.mediatorAlta = mediatorPerfil;
    }
    public void guardarPaciente(Paciente paciente){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Singleton.url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        PacienteInterfaceApi pacienteInterfaceApi = retrofit.create(PacienteInterfaceApi.class);

        Call<ResponseBody> call = pacienteInterfaceApi.guardarPaciente(paciente.getNombre_pac(),paciente.getUrgente(),paciente.getTipoUsuario(),paciente.getCedula_pac(),paciente.getApellido_pac(),paciente.getNombreUsuario(),paciente.getPassword(),paciente.getTelefono_pac(),paciente.getEdad_pac(),paciente.getNivelGlucosa(),paciente.getSexo_pac(),1);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.body()!= null){
                    System.out.println();
                    System.out.println();
                    System.out.println();

                    try {
                        Singleton.paciente.setId_pac(Integer.valueOf(response.body().string()));
                        System.out.println("Fuck Yes PAciente Guardado "+ response.body().string());
                        Toast toast = Toast.makeText(mediatorAlta.formularioPaciente.getApplicationContext(), "Paciente Guardado", Toast.LENGTH_SHORT);
                        toast.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }



            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("Erorrrr paciente"+t.getMessage());
                Toast toast = Toast.makeText(mediatorAlta.revisarControl.getApplicationContext(), "Error al guardar paciente", Toast.LENGTH_SHORT);
                toast.show();
            }


        });

    }


    public void getPaciente(String cedula) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Singleton.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PacienteInterfaceApi pacienteInterfaceApi = retrofit.create(PacienteInterfaceApi.class);

        Call<Paciente> call = pacienteInterfaceApi.getPaciente(cedula);

        final Paciente[] paciente = new Paciente[1];
        call.enqueue(new Callback<Paciente>() {
            @Override
            public void onResponse(Call<Paciente> call, Response<Paciente> response) {
                if(response.body() != null){
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    paciente[0]  = response.body();
                    System.out.println("Fuck Yes"+ paciente[0].getApellido_pac());
                    Singleton.paciente = paciente[0];
                    GestionDosis gestionDosis = new GestionDosis(mediatorLoggin);
                    gestionDosis.getDosisActual(Singleton.paciente.getId_pac());

                    return;
                }


            }

            @Override
            public void onFailure(Call<Paciente> call, Throwable t) {
                System.out.println("Erorrrr"+t.getMessage());
            }
        });

    }

    public void getPacientes(String cedulaDoctor){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Singleton.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PacienteInterfaceApi pacienteInterfaceApi = retrofit.create(PacienteInterfaceApi.class);

        Call<ArrayList<Paciente>> call = pacienteInterfaceApi.getPacientes(cedulaDoctor);


        call.enqueue(new Callback<ArrayList<Paciente>>() {
            @Override
            public void onResponse(Call<ArrayList<Paciente>> call, Response<ArrayList<Paciente>> response) {
                if(response.body() != null){
                    System.out.println();
                    System.out.println();
                    System.out.println();

                    System.out.println("Fuck Yes"+ response.body().get(0).getApellido_pac());

                    mediatorHistorial.historialPacientes.setListaPacientes(response.body());

                }

            }

            @Override
            public void onFailure(Call<ArrayList<Paciente>> call, Throwable t) {
                System.out.println("Erorrrr"+t.getMessage());
            }


        });
    }
    public void getTodosPacientes(String cedulaDoctor){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Singleton.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PacienteInterfaceApi pacienteInterfaceApi = retrofit.create(PacienteInterfaceApi.class);

        Call<ArrayList<Paciente>> call = pacienteInterfaceApi.getTodosPacientes();


        call.enqueue(new Callback<ArrayList<Paciente>>() {
            @Override
            public void onResponse(Call<ArrayList<Paciente>> call, Response<ArrayList<Paciente>> response) {
                if(response.body() != null){
                    System.out.println();
                    System.out.println();
                    System.out.println();

                    System.out.println("Fuck Yes"+ response.body().get(0).getApellido_pac());
                    mediatorHistorial.historialPacientes.setListaPacientes(response.body());

                }

            }

            @Override
            public void onFailure(Call<ArrayList<Paciente>> call, Throwable t) {
                System.out.println("Erorrrr"+t.getMessage());
            }


        });
    }
}
