package com.example.diabetes.Network.Interface;

import com.example.diabetes.Modelo.Dosis;
import com.example.diabetes.Modelo.Paciente;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DosisInterfaceApi {
    @Headers({
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: Retrofit-Sample-App"
    })


    @GET("getListaDosis/{idPaciente}")
    Call<ArrayList<Dosis>> getListaDosis(@Path("idPaciente") int id);
    @GET("getDosisActual/{idPaciente}")
    Call<Dosis> getDosisActual(@Path("idPaciente") int id);
    @FormUrlEncoded
    @POST("setDosis")
    Call<ResponseBody> setDosis(@Field("nph") double nph, @Field("rapida") double rapida, @Field("id_pac") int id_pac);



}
