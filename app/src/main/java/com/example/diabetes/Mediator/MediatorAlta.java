package com.example.diabetes.Mediator;

import android.widget.Toast;

import com.example.diabetes.Modelo.Control;
import com.example.diabetes.Modelo.Doctor;
import com.example.diabetes.Modelo.Dosis;
import com.example.diabetes.Modelo.Glicemia;
import com.example.diabetes.Network.Gestion.GestionControl;
import com.example.diabetes.Network.Gestion.GestionDosis;
import com.example.diabetes.Network.Gestion.GestionGlicemia;
import com.example.diabetes.Singleton;
import com.example.diabetes.View.FormularioDoctor;
import com.example.diabetes.Modelo.Paciente;
import com.example.diabetes.Network.Gestion.GestionDoctor;
import com.example.diabetes.Network.Gestion.GestionPaciente;
import com.example.diabetes.View.FormularioDosis;
import com.example.diabetes.View.FormularioGlicemia;
import com.example.diabetes.View.FormularioPaciente;
import com.example.diabetes.View.RevisarControl;
import com.example.diabetes.utils.Error;
import com.example.diabetes.utils.Validate;

public class MediatorAlta implements Mediator {
    public FormularioPaciente formularioPaciente;
    public FormularioDoctor formularioDoctor;
    public FormularioDosis formularioDosis;
    public RevisarControl revisarControl;
    public FormularioGlicemia formularioGlicemia;

    public MediatorAlta(FormularioGlicemia formularioGlicemia) {
        this.formularioGlicemia = formularioGlicemia;
    }

    public MediatorAlta(RevisarControl revisarControl) {
        this.revisarControl = revisarControl;
    }

    public MediatorAlta(FormularioDosis formularioDosis) {
        this.formularioDosis = formularioDosis;
    }

    public MediatorAlta(FormularioPaciente formularioPaciente) {
        this.formularioPaciente = formularioPaciente;
    }

    public MediatorAlta(FormularioDoctor formularioDoctor) {
        this.formularioDoctor = formularioDoctor;
    }

    @Override
    public void notificar(String evento) {
        if (evento.equals( "GuardarPaciente")){
            GestionPaciente gestionPaciente = new GestionPaciente(this);
            Paciente paciente = new Paciente(0,"3",
                    formularioPaciente.getEtCedula().getText().toString(),
                    formularioPaciente.getEtNombre().getText().toString(),
                    0,
                    formularioPaciente.getEtApellido().getText().toString(),
                    formularioPaciente.getEtNombre().getText().toString(),
                    formularioPaciente.getEtTelefono().getText().toString(),
                    0,
                    Integer.parseInt(formularioPaciente.getEtEdad().getText().toString()),
                    String.valueOf(formularioPaciente.getSpinnerSexo().getSelectedItem().toString().charAt(0))
                    ,1,
                    formularioPaciente.getEtContrasenia().getText().toString());
            Error error = Validate.validatePaciente(paciente);
            if(error.getValidate()) {
                Singleton.paciente = paciente;
                gestionPaciente.guardarPaciente(paciente);
            }else{
                Toast toast1 = Toast.makeText(formularioPaciente.getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT);
                toast1.show();
            }

        }
        else if (evento.equals("GuardarDoctor")){

            GestionDoctor gestionDoctor = new GestionDoctor(this);
            Doctor doctor = new Doctor(1,formularioDoctor.etNombre.getText().toString(),
                                        "2", formularioDoctor.etCedula.getText().toString(),
                                        formularioDoctor.etApellido.getText().toString(),
                                        formularioDoctor.etNombre.getText().toString(),
                                        formularioDoctor.etTelefono.getText().toString());
            System.out.println("GuardarDoctor"+ doctor.toString());
            gestionDoctor.guardarDoctor(doctor,formularioDoctor.etPass.getText().toString());
        }
        else if( evento.equals("GuardarDosis")){
            GestionDosis gestionDosis = new GestionDosis(this);
            Dosis dosis = new Dosis(Double.valueOf(formularioDosis.etNph.getText().toString()),Double.valueOf(formularioDosis.etRapida.getText().toString()));
            gestionDosis.setDosis(dosis, Singleton.paciente.getId_pac());
        }
        else if(evento.equals("GuardarRevisionControl")){
            GestionControl gestionControl = new GestionControl(this);
            Control control = new Control(Singleton.idControl,revisarControl.spEstado.getSelectedItem().toString(),revisarControl.spDecision.getSelectedItem().toString(),Double.valueOf(revisarControl.etNph.getText().toString()),Double.valueOf(revisarControl.etRapida.getText().toString()),revisarControl.etObservacoines.getText().toString());
            gestionControl.revisarControl(control);
        }else if(evento.equals("GuardarGlicemia")){
            GestionGlicemia getionGlicemia = new GestionGlicemia(this);
            int opcion = 0;
            if(formularioGlicemia.spinnerHorario.getSelectedItem().toString().equals("Desayuno"))
                opcion = 1;
            if(formularioGlicemia.spinnerHorario.getSelectedItem().toString().equals("Almuerzo"))
                opcion = 2;
            if(formularioGlicemia.spinnerHorario.getSelectedItem().toString().equals("Merienda"))
                opcion = 3;
           Error error =  Validate.validateGlicemia(formularioGlicemia.etNivelGlucosa.getText().toString());
           if(error.getValidate()) {
               Singleton.paciente.setNivelGlucosa(Double.valueOf(formularioGlicemia.etNivelGlucosa.getText().toString()));

               getionGlicemia.guardarGlicemia(Integer.parseInt(formularioGlicemia.etNivelGlucosa.getText().toString()), opcion);
           }else{
               Toast toast1 = Toast.makeText(formularioPaciente.getApplicationContext(),
                       error.getMessage(), Toast.LENGTH_SHORT);
               toast1.show();
           }
        }

    }
}
