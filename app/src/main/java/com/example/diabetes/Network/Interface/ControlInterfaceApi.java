package com.example.diabetes.Network.Interface;

import com.example.diabetes.Modelo.Control;
import com.example.diabetes.Modelo.Paciente;
import com.example.diabetes.Modelo.Verify;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ControlInterfaceApi {
    @Headers({
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: Retrofit-Sample-App"
    })

    @GET("getControles/{idPaciente}")
    Call<ArrayList<Control>> getControles(@Path("idPaciente") int id);
    @FormUrlEncoded
    @POST("revisar")
    Call<ResponseBody> revisarControl(@Field("id_control") int idControl, @Field("estadoPaciente") String estadoPaciente,@Field("decision") String decision,@Field("nphActual") double nphActual,@Field("rapidaActual") double rapidaActual,@Field("observaciones") String observaciones,@Field("id_pac")int idPac,@Field("id_doc") int idDoc);
}
