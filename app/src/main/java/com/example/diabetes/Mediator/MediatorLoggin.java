package com.example.diabetes.Mediator;

import android.content.Intent;
import android.widget.Toast;

import com.example.diabetes.Network.Gestion.GestionAdmin;
import com.example.diabetes.Network.Gestion.GestionDosis;
import com.example.diabetes.Singleton;
import com.example.diabetes.View.MainActivity;
import com.example.diabetes.Modelo.Verify;
import com.example.diabetes.Network.Gestion.GestionDoctor;
import com.example.diabetes.Network.Gestion.GestionPaciente;
import com.example.diabetes.Network.Gestion.Loggin;
import com.example.diabetes.View.Perfil;
import com.example.diabetes.utils.Error;
import com.example.diabetes.utils.Validate;

public class MediatorLoggin implements Mediator {

    Boolean logueado = false;
    Verify verify;

    public MainActivity mainActivity;

    public MediatorLoggin(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        verify = new Verify();
    }

    @Override
    public void notificar(String evento) {
        if (evento == "loggin"){
            System.out.println(mainActivity.getEtNombreUsuario().getText().toString());
            if (mainActivity.getEtNombreUsuario().getText().toString().equals("doctor")){
                Loggin loggin = new Loggin(this);
                loggin.verifyLoggin("1723305254","medicina");
            }else if (mainActivity.getEtNombreUsuario().getText().toString().equals("admin")){
                Loggin loggin = new Loggin(this);
                loggin.verifyLoggin("1750428227","lduquito");
            }else{
                Loggin loggin = new Loggin(this);
                loggin.verifyLoggin("887798","manchester");
            }

//            Error error =  Validate.validateLogin(mainActivity.getEtNombreUsuario().getText().toString(),mainActivity.getEtContrasenia().getText().toString());
//            if(error.getValidate()) {
//                Loggin loggin = new Loggin(this);
//                loggin.verifyLoggin(mainActivity.getEtNombreUsuario().getText().toString(), mainActivity.getEtContrasenia().getText().toString());
//
//            }else{
//                Toast toast1 = Toast.makeText(mainActivity.getApplicationContext(),
//                        error.getMessage(), Toast.LENGTH_SHORT);
//                toast1.show();
//            }
        }else if(evento.equals("PacienteCargado")){
            Intent intent = new Intent(mainActivity, Perfil.class);
            mainActivity.startActivity(intent);
        }

    }

    public void iniciarIntentPerfil(Verify verify){
        if (verify.getLoggueado()=="true"){
            Singleton.verify = verify;
            if (verify.getTipoUsuario().equals("paciente")){
                GestionPaciente gestionPaciente = new GestionPaciente(this);
                gestionPaciente.getPaciente("887798");
            }else if (verify.getTipoUsuario().equals("doctor")){
                GestionDoctor gestionDoctor = new GestionDoctor(this);
                gestionDoctor.getDoctor("1723305254");
            }
            else if (verify.getTipoUsuario().equals("admin")){
                GestionAdmin gestionAdmin = new GestionAdmin(this);
                gestionAdmin.getAdmin("1750428227");
            }

        }

    }
    public Verify getVerify() {

        return verify;
    }

    public void setVerify(Verify verify) {
        this.verify = verify;
        this.iniciarIntentPerfil(this.verify);
    }
}
