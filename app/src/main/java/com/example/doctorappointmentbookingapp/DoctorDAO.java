package com.example.doctorappointmentbookingapp;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

public class DoctorDAO {

    private LinkedList<Doctor> allDoctors;

    public DoctorDAO(){
        allDoctors = new LinkedList<>();
    }

    public LinkedList<Doctor> getDoctors() {
        FirebaseDatabase.getInstance().getReference("Doctors").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    Doctor getDoctor = snapshot1.getValue(Doctor.class);
                    Log.i("***********", getDoctor.toString());
                    allDoctors.add(getDoctor);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return allDoctors;
    }
}
