package com.example.diabetes.Network.Interface;

import com.example.diabetes.Modelo.Admin;
import com.example.diabetes.Modelo.Doctor;
import com.example.diabetes.Modelo.Paciente;
import com.example.diabetes.Modelo.Verify;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AdminInterfaceApi {
    @Headers({
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: Retrofit-Sample-App"
    })
    @GET("admin/{cedula}")
    Call<Admin> getAdmin(@Path("cedula") String cedula);




}
