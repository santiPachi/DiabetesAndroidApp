package com.example.diabetes.Network.Interface;

import com.example.diabetes.Modelo.Doctor;
import com.example.diabetes.Modelo.Verify;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DoctorInterfaceApi {
    @Headers({
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: Retrofit-Sample-App"
    })
    @GET("doctor/{cedula}")
    Call<Doctor> getMedico(@Path("cedula") String cedula);
    @GET("getTodosDoctores")
    Call<ArrayList<Doctor>> getTodosDoctores();

    @FormUrlEncoded
    @POST("setDoctor")
    Call<Doctor> guardarDoctor(@Field("nombre") String nombre, @Field("tipoUsuario") String tipoUsuario, @Field("cedula_doc") String cedula_doc, @Field("apellido_doc") String apellido_doc, @Field("nombreUsuario") String nombreUsuario,  @Field("password") String password,@Field("telefono_doc") String telefono_doc);


}
