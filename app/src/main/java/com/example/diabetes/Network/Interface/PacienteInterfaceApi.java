package com.example.diabetes.Network.Interface;

import com.example.diabetes.Modelo.Doctor;
import com.example.diabetes.Modelo.Paciente;
import com.example.diabetes.Modelo.Verify;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PacienteInterfaceApi {
    @Headers({
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: Retrofit-Sample-App"
    })
    @GET("paciente/{cedula}")
    Call<Paciente> getPaciente(@Path("cedula") String cedula);

    @GET("getPacientes/{cedula}")
    Call<ArrayList<Paciente>> getPacientes(@Path("cedula") String cedula);
    @GET("getTodosPacientes")
    Call<ArrayList<Paciente>> getTodosPacientes();
    @FormUrlEncoded
    @POST("setPaciente")
    Call<ResponseBody> guardarPaciente(@Field("nombre") String nombre, @Field("urgente") Integer urgente , @Field("tipoUsuario") String tipoUsuario, @Field("cedula_pac") String cedula_pac, @Field("apellido_pac") String apellido_pac, @Field("nombreUsuario") String nombreUsuario, @Field("password") String password, @Field("telefono_pac") String telefono_pac, @Field("edad_pac") Integer edad_pac, @Field("nivelGlucosa") double nivelGlucosa, @Field("sexo_pac") String sexo_pac, @Field("id_doc") Integer id_doc);



}
