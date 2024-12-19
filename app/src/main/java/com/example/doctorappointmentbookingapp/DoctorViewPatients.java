package com.example.doctorappointmentbookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DoctorViewPatients extends AppCompatActivity {


    private List<Patient> patients;

    private ListView listview;

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_view_patients);

        patients = new ArrayList<>();
        listview = (ListView) findViewById(R.id.listViewPatients);

        getPatients();
    }

    private void getPatients(){
        FirebaseDatabase.getInstance().getReference("patients")
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                patients.clear();
                for (DataSnapshot child: snapshot.getChildren()) {
                    Patient patient = child.getValue(Patient.class);
                    patients.add(patient);
                }
                PatientListAdapter adapter = new PatientListAdapter(context, R.layout.patient_item_layout, patients);
                listview.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });

    }
}