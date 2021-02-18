package com.example.diabetes.Network.Gestion;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import com.example.diabetes.Mediator.MediatorCalorias;
import com.example.diabetes.Modelo.Comida;

import com.example.diabetes.Singleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.ResponseBody;


public class GestionCalorias {

    MediatorCalorias mediatorCalorias;

    public GestionCalorias(MediatorCalorias mediatorCalorias) {
        this.mediatorCalorias = mediatorCalorias;
    }

    public GestionCalorias() {
    }

    public void setImage(Bitmap image) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        // Read BitMap by file path

        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        RequestBody postBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", "androidFlask.jpg", RequestBody.create(MediaType.parse("image/*jpg"), byteArray))
                .build();

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(Singleton.urlPY+"setImage")
                .post(postBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Cancel the post on failure.
                call.cancel();

                // In order to access the TextView inside the UI thread, the code is executed inside runOnUiThread()

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                // In order to access the TextView inside the UI thread, the code is executed inside runOnUiThread()
                String jsonData = response.body().string();
                JSONObject Jobject = null;
                try {
                    Jobject = new JSONObject(jsonData);

                    Comida com = new Comida(Jobject.get("nombre").toString(),Jobject.get("calorias").toString(),Jobject.get("acc").toString(),Jobject.get("porcion").toString(),Jobject.get("unidad").toString());
                    System.out.println(com);
                    mediatorCalorias.setComida(com);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }


}
