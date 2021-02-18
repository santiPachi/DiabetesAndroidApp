package com.example.diabetes.View;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.diabetes.Mediator.MediatorCalorias;
import com.example.diabetes.R;
import com.example.diabetes.Singleton;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Perfil extends AppCompatActivity {
    Button btGlicemia;
    Button btHistorial;
    Button btAjustes;
    Button btCalorias;
    TextView tvNombre;
    TextView tvApellido;
    TextView tvNph;
    TextView tvRapida;
    ImageView imgSemaforo;
    String currentPhotoPath = null;
    Uri imageUri = null;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_SET_GLICEMIA = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);


        btCalorias = findViewById(R.id.bt_contador_cal);
        btAjustes = findViewById(R.id.bt_ajustes);
        btGlicemia = findViewById(R.id.bt_glicemia);
        btHistorial = findViewById(R.id.bt_historial);
        tvNombre = findViewById(R.id.tx_nombre);
        imgSemaforo = findViewById(R.id.img_semaforo);
        tvNph = findViewById(R.id.tx_nph_perfil);
        tvRapida = findViewById(R.id.tx_rapida_perfil);
        tvApellido = findViewById(R.id.tx_apellido);

        tvNombre.setText(Singleton.paciente.getNombre_pac());
        tvApellido.setText(Singleton.paciente.getApellido_pac());
        tvRapida.setText(String.valueOf(Singleton.dosis.getRapida()));
        tvNph.setText(String.valueOf(Singleton.dosis.getNph()));

        setImgSemaforo();
        btHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BitacoraPaciente.class);
                startActivity(intent);
            }
        });

        btGlicemia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),FormularioGlicemia.class);
                startActivityForResult(intent,REQUEST_SET_GLICEMIA);
            }
        });

        btCalorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(cameraIntent.resolveActivity(getPackageManager())!=null){
                    File imageFile = getImageFile();
                    if(imageFile!=null){
                        imageUri = FileProvider.getUriForFile(getApplicationContext(),"com.example.diabetes.fileprovider",imageFile);
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                        startActivityForResult(cameraIntent,REQUEST_IMAGE_CAPTURE);
                    }
                }
            }
        });


    }
    private File getImageFile(){
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageName = "jpg_"+timestamp+"_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imageFile = null;
        try {
            imageFile = File.createTempFile("image",".jpg",storageDir);
        } catch (IOException e) {
            e.printStackTrace();

        }
        currentPhotoPath = imageFile.getAbsolutePath();
        return imageFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(
                            getContentResolver(), imageUri);

                    Matrix matrix = new Matrix();

                    matrix.postRotate(90);



                    MediatorCalorias mediatorCalorias = new MediatorCalorias(bitmap,this,currentPhotoPath);
                    mediatorCalorias.notificar("ReqCalcularCalorias");
                } catch (IOException e) {
                    e.printStackTrace();
                }



                //

            }
        }else if(resultCode == 0){
            if(requestCode == REQUEST_SET_GLICEMIA){
               setImgSemaforo();
        }
    }
    }
    public void setImgSemaforo(){
        if (Singleton.semaforo.getColor().equals("rojo")){
            imgSemaforo.setImageResource(R.drawable.rojo);
        }else if(Singleton.semaforo.getColor().equals("amarillo")){
            imgSemaforo.setImageResource(R.drawable.amarillo);
        }else{
            imgSemaforo.setImageResource(R.drawable.verde);
        }
    }

}
