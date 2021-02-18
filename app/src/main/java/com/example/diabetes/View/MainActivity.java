package com.example.diabetes.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.diabetes.Mediator.MediatorLoggin;
import com.example.diabetes.R;

import java.sql.SQLOutput;

    public class MainActivity extends AppCompatActivity {
    Button btLogin;
    Button btRegistrase;
    EditText etNombreUsuario;
    EditText etContrasenia;

    MediatorLoggin mediatorLoggin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediatorLoggin = new MediatorLoggin(this);

        btRegistrase = findViewById(R.id.bt_registrarse);
        btLogin = findViewById(R.id.bt_login);
        etContrasenia = findViewById(R.id.et_nombre_usuario);
        etNombreUsuario = findViewById(R.id.et_nombre_usuario);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediatorLoggin.notificar("loggin");
            }
        });

    }

        public EditText getEtNombreUsuario() {
            return etNombreUsuario;
        }



        public EditText getEtContrasenia() {
            return etContrasenia;
        }


    }
