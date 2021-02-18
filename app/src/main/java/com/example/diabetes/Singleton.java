package com.example.diabetes;

import com.example.diabetes.Modelo.Admin;
import com.example.diabetes.Modelo.Doctor;
import com.example.diabetes.Modelo.Dosis;
import com.example.diabetes.Modelo.Paciente;
import com.example.diabetes.Modelo.Semaforo;
import com.example.diabetes.Modelo.Verify;

public  class Singleton {
    public static String url = "http://192.168.1.111:3000/";
    public static String urlPY = "http://192.168.1.111:5000/";
    public static Semaforo semaforo;
    public static Paciente paciente;
    public static Doctor doctor;
    public static Admin admin;
    public static Verify verify;
    public static int idControl;
    public static Dosis dosis;


}
