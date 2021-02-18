package com.example.diabetes.Network.Gestion;

import android.widget.Toast;

import com.example.diabetes.Mediator.Mediator;
import com.example.diabetes.Mediator.MediatorHistorial;
import com.example.diabetes.Mediator.MediatorLoggin;
import com.example.diabetes.Mediator.MediatorAlta;
import com.example.diabetes.Modelo.Control;

import com.example.diabetes.Network.Interface.ControlInterfaceApi;

import com.example.diabetes.Singleton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class GestionControl {

    MediatorHistorial  mediatorHistorial;
    MediatorAlta mediatorAlta;

    public GestionControl(MediatorAlta mediatorAlta) {
        this.mediatorAlta = mediatorAlta;
    }

    public GestionControl(MediatorHistorial mediatorHistorial) {
        this.mediatorHistorial = mediatorHistorial;
    }




    public void getControles(int idPaciente){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Singleton.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ControlInterfaceApi controlInterfaceApi = retrofit.create(ControlInterfaceApi.class);

        Call<ArrayList<Control>> call = controlInterfaceApi.getControles(idPaciente);


        call.enqueue(new Callback<ArrayList<Control>>() {
            @Override
            public void onResponse(Call<ArrayList<Control>> call, Response<ArrayList<Control>> response) {
                if(response.body() != null){
                    System.out.println();
                    System.out.println();
                    System.out.println();

                    System.out.println("Fuck Yes");

                    mediatorHistorial.bitacoraPaciente.setListaControles(response.body());

                }
                System.out.println("Fuck no");

            }

            @Override
            public void onFailure(Call<ArrayList<Control>> call, Throwable t) {
                System.out.println("Erorrrr"+t.getMessage());
            }


        });
    }public void revisarControl(Control control) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Singleton.url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        ControlInterfaceApi controlInterfaceApi = retrofit.create(ControlInterfaceApi.class);

        Call<ResponseBody> call = controlInterfaceApi.revisarControl(Singleton.idControl,control.getEstadoPaciente(),control.getDecision(),control.getNphActual(),control.getRapidaActual(),control.getObservaciones(),Singleton.paciente.getId_pac(),Singleton.doctor.getId());

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    System.out.println();
                    System.out.println();
                    System.out.println();

                    System.out.println("Fuck Yes");

                    Toast toast = Toast.makeText(mediatorAlta.revisarControl.getApplicationContext(), "Revisión Guardada", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }



            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("Erorrrr"+t.getMessage());
                Toast toast = Toast.makeText(mediatorAlta.revisarControl.getApplicationContext(), "Error al guardar Revisión", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }


}
