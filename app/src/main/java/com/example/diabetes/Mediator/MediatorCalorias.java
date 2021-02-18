package com.example.diabetes.Mediator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import com.example.diabetes.Modelo.Comida;
import com.example.diabetes.Network.Gestion.GestionCalorias;
import com.example.diabetes.View.CamaraCalorias;
import com.example.diabetes.View.Perfil;
import com.example.diabetes.utils.ScaleBitmap;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MediatorCalorias implements  Mediator{

    public Bitmap rotatedBitmap;
    Comida comida;
    Perfil perfilPaciente;
    String path;
    public MediatorCalorias(Bitmap rotatedBitmap, Perfil perfilPaciente,String path) {
        this.rotatedBitmap = rotatedBitmap;
        this.perfilPaciente =  perfilPaciente;
        this.path = path;
    }

    @Override
    public void notificar(String evento) {
        if (evento.equals("ReqCalcularCalorias")) {

            GestionCalorias gestionCalorias = new GestionCalorias(this);

            gestionCalorias.setImage(rotatedBitmap);
        }
    }

    public Comida getComida() {

        return comida;
    }

    public void setComida(Comida comida) {
        this.comida = comida;
        Intent intent = new Intent(perfilPaciente.getApplicationContext(),CamaraCalorias.class);
        intent.putExtra("Comida",comida);
        intent.putExtra("path",path);
        perfilPaciente.startActivity(intent);

    }
}
