package com.example.diabetes.Network.Interface;

import com.example.diabetes.Modelo.Glicemia;
import com.example.diabetes.Modelo.Semaforo;
import com.example.diabetes.Modelo.Verify;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GlicemiaInterfaceApi {

    @FormUrlEncoded
    @POST("setGlicemia")
    Call<Semaforo> setGlicemia(@Field("fecha") String fecha, @Field("nivelGlucosa") int nivelGlucosa, @Field("ayunas") int ayunas, @Field("desayuno") int desayuno, @Field("almuerzo") int almuerzo, @Field("merienda") int merienda, @Field("observaciones") String observaciones, @Field("id_pac") int id_pac);

    @GET("getGlicemia/{idControl}")
    Call<ArrayList<Glicemia>> getGlicemias(@Path("idControl") int idControl);

}
