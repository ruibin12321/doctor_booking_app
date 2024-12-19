package com.example.doctorappointmentbookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PatientViewAppointmentActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.b07project.USER";
    Patient patient;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_view_appointment);

        //Catch intent
        Bundle extras = getIntent().getExtras();
        patient = (Patient) extras.getSerializable(EXTRA_MESSAGE);

        ListView appointList = findViewById(R.id.appointList);
        ArrayList<String> list = new ArrayList<>();

        //Search for appointments including patient
        DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Appointments");
        ValueEventListener listener2 = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot){
                list.clear();
                for(DataSnapshot child : snapshot.getChildren()){
                    Appointment appt = child.getValue(Appointment.class);
                    if(appt.getPatientHealthCard() == patient.healthCardNumber){
                        //Print Appointment information
                        list.add(appt.getBookingDate() + " " + appt.getDuration() + " with " + "Dr." + appt.getDoctorName());



                    }
                }
                if(list.isEmpty()){
                    list.add("No upcoming appointments");
                }


                //Display in ListView
                ArrayAdapter<String> adapter = new ArrayAdapter<>(context,android.R.layout.simple_list_item_1,list);
                appointList.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                list.clear();
                list.add("Database error");
                ArrayAdapter<String> adapter = new ArrayAdapter<>(context,android.R.layout.simple_list_item_1,list);
                appointList.setAdapter(adapter);
            }
        };
        ref2.addValueEventListener(listener2);

    }
}
