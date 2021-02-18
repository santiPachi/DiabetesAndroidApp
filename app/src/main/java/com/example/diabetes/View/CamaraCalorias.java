package com.example.diabetes.View;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diabetes.Mediator.MediatorCalorias;
import com.example.diabetes.Modelo.Comida;
import com.example.diabetes.Network.Gestion.GestionCalorias;
import com.example.diabetes.R;

import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

public class CamaraCalorias extends AppCompatActivity {
    ImageView imgCalorias;

    public TextView txNombreComida;
    public TextView txUnidad;
    public TextView txCalorias;
    public TextView txPorcion;

    public MediatorCalorias mediatorCalorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara_calorias);
        imgCalorias = findViewById(R.id.img_calorias);
        txNombreComida = findViewById(R.id.tx_nombre_comida);
        txUnidad = findViewById(R.id.tx_unidad_comida);
        txPorcion = findViewById(R.id.tx_porcion_comida);
        txCalorias = findViewById(R.id.tx_calorias_comida);

        Comida comida = (Comida)getIntent().getSerializableExtra("Comida");
        String path = (String) getIntent().getStringExtra("path");
        txNombreComida.setText(comida.getNombre());
        txPorcion.setText(comida.getPorcion());
        txUnidad.setText(comida.getUnidad());
        txCalorias.setText(comida.getCalorias());
        Bitmap myBitmap = BitmapFactory.decodeFile(path);
        imgCalorias.setImageBitmap(myBitmap);


    }




}
