package com.example.doctorappointmentbookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PatientViewDoctors extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.b07project.USER";
    Patient patient;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_view_doctors);

        Bundle extras = getIntent().getExtras();
        patient = (Patient) extras.getSerializable(EXTRA_MESSAGE);

        ListView docList = findViewById(R.id.docList);
        ArrayList<String> list = new ArrayList<>();


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Appointments");
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot){
                list.clear();
                for(DataSnapshot child : snapshot.getChildren()){
                    Appointment appt = child.getValue(Appointment.class);
                    if(patient.healthCardNumber == appt.getPatientHealthCard()){
                        if (list.contains("Dr. " + appt.getDoctorName()) == false){
                            list.add("Dr. " + appt.getDoctorName());
                        }
                    }
                }

                if(list.isEmpty()){
                    list.add("No previous doctors.");
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(context,android.R.layout.simple_list_item_1,list);
                docList.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                list.clear();
                list.add("Database error");
                ArrayAdapter<String> adapter = new ArrayAdapter<>(context,android.R.layout.simple_list_item_1,list);
                docList.setAdapter(adapter);
            }

        };
        ref.addValueEventListener(listener);
    }
}