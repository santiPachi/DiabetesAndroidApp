package com.example.diabetes.Network.Interface;

import com.example.diabetes.Modelo.Paciente;
import com.example.diabetes.Modelo.Semaforo;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SemaforoInterfaceApi {
    @Headers({
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: Retrofit-Sample-App"
    })
    @GET("getActualSemaforo/{idPaciente}")
    Call<Semaforo> getSemaforoActual(@Path("idPaciente") int idPac);



}
