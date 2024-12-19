package com.example.doctorappointmentbookingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


public class Model {

    private DatabaseReference refPatient;
    private DatabaseReference refDoctor;

    public Model(){
        refPatient = FirebaseDatabase.getInstance().getReference("patients");
        refDoctor = FirebaseDatabase.getInstance().getReference("Doctors");
    }

    public void fetchPatient(String username, String password, Consumer<Patient> callback) {

        refPatient.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    Patient patient = (Patient) child.getValue(Patient.class);

                    if (patient.getUsername().equals(username) &&
                            patient.getPassword().equals(password)) {

                        callback.accept(patient);

                    }
                }
                callback.accept(null);
            }


            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Model", "failed to fetch patient");
                callback.accept(null);
            }
        });

    }

    public void fetchDoctor(String username, String password, Consumer<Doctor> callback) {

        refDoctor.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    Doctor doctor = (Doctor) child.getValue(Doctor.class);

                    if (doctor.getUsername().equals(username) &&
                            doctor.getPassword().equals(password)) {

                        callback.accept(doctor);
                        return;
                    }
                }
                callback.accept(null);
            }


            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Model", "failed to fetch doctor");
                callback.accept(null);
            }
        });
    }

}
