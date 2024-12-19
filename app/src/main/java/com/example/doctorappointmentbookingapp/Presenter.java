package com.example.doctorappointmentbookingapp;

import android.app.Activity;
import android.content.Intent;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class Presenter {
    private Model model;
    private MainActivity view;

    public Presenter(Model model, MainActivity view){
        this.model = model;
        this.view = view;
    }

    public void loginIntoDashBoardAsPatient() {

        String username = view.getUser();
        String password = view.getPass();

        model.fetchPatient(username, password, (Patient patient) -> {
            if (patient != null){
                view.displayMessage("");
                view.redirectToPatientPage(patient);
            }
            else{
                view.displayMessage("Invalid username or password");
            }
        });
    }

    public void loginIntoDashBoardAsDoctor() {

        String username = view.getUser();
        String password = view.getPass();

        model.fetchDoctor(username, password, (Doctor doctor) -> {

            if (doctor != null){
                view.displayMessage("");
                view.redirectToDoctorPage(doctor);
            }
            else{
                view.displayMessage("Invalid username or password");
            }

        });
    }

}
