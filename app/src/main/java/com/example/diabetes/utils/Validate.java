package com.example.diabetes.utils;

import com.example.diabetes.Modelo.Paciente;

public class Validate {
    public static Error validateLogin(String ci,String password){
        if(password.isEmpty()){
            return new Error(false,"Contraseña en blanco");
        }
        if(ci.isEmpty()){
            return new Error(false,"CI en blanco");
        }

        Error error = validateCI(ci);
        if(!error.getValidate()){
            return error;
        }

        return new Error(true,"");
    }
    public static Error validateGlicemia(String nivel){
        if(nivel.isEmpty()){
            return new Error(false,"Glicemia en blanco");
        }

        if(!Validate.isNumeric(nivel)){
            return new Error(false,"Glicemia debe tener caracteres numéricos");
        }
        Integer niv = Integer.parseInt(nivel);
        if(niv<0 || niv>200){
            return new Error(false,"Glicemia fuera de rango");
        }
        return new Error(true,"");
    }
    public static Error validatePaciente(Paciente paciente){
        if(paciente.getNombreUsuario().isEmpty()){
            return new Error(false,"Nombre en blanco");
        }
        if(paciente.getApellido_pac().isEmpty()){
            return new Error(false,"Apellido en blanco");
        }
        if(paciente.getTelefono_pac().isEmpty()){
            return new Error(false,"Telefono en blanco");
        }
        if(paciente.getSexo_pac().isEmpty()){
            return new Error(false,"Sexo en blanco");
        }


        Error error = validateCI(paciente.getCedula_pac());
        if(!error.getValidate()){
            return error;
        }
         error = validateAge(paciente.getEdad_pac());
        if(!error.getValidate()){
            return error;
        }
         error = validatePassword(paciente.getPassword());
        if(!error.getValidate()){
            return error;
        }

        return new Error(true,"");
    }

    public static Error validateAge(Integer age) {

        if(age<0 || age>120){
            return new Error(false,"Edad fuera de rango");
        }
        return new Error(true,"");
    }
//    public static Error validateGlicemia(String glicemia) {
//        if(ci.length() == 10){
//            return new Error(false,"CI debe tener 10 dígitos");
//        }
//        if(!Validate.isNumeric(ci)){
//            return new Error(false,"CI debe tener caracteres numéricos");
//        }
//        return new Error(true,"");
//    }
        public static Error validatePassword(String password) {
            if(password.length() == 8){
                return new Error(false,"Contraseña debe tener 8 dígitos");
            }

            return new Error(true,"");
        }

    public static Error validateCI(String ci) {
                if(ci.length() == 10){
            return new Error(false,"CI debe tener 10 dígitos");
        }
        if(!Validate.isNumeric(ci)){
            return new Error(false,"CI debe tener caracteres numéricos");
        }
        return new Error(true,"");
    }
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
