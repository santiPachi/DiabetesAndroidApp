package com.example.diabetes.Network.Interface;

import com.example.diabetes.Modelo.Paciente;
import com.example.diabetes.Modelo.Verify;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;

import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
public interface LogginInterfaceApi {

    @FormUrlEncoded
    @POST("verify")
    Call<Verify> loggin(@Field("cedula") String cedula, @Field("password") String contrasenia);

}
