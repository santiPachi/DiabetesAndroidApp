package com.example.diabetes.Network.Interface;

import com.example.diabetes.Modelo.Verify;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface CaloriasInterfaceApi {
    @Multipart
    @POST("uploadImage")
    Call<ResponseBody> setImage(@Part("file\"; fileName=\"myFile.png\" ") RequestBody requestBodyFile, @Part("image") RequestBody requestBodyJson);


}
