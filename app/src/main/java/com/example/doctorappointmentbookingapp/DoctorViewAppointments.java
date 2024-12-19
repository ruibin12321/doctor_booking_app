package com.example.doctorappointmentbookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DoctorViewAppointments extends AppCompatActivity {

    private Doctor doctor;
    private List<Appointment> appointments;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_view_appointments);

        doctor = (Doctor) getIntent().getSerializableExtra(MainActivity.EXTRA_MESSAGE);

        listView = (ListView) findViewById(R.id.listviewAppointment);
        appointments = new ArrayList<>();

        getAppointments();

    }


    private void getAppointments(){
        FirebaseDatabase.getInstance().getReference("Appointments")
                .orderByChild("doctorHealthCard").equalTo(doctor.getHealthCardNumber())
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                appointments.clear();
                for (DataSnapshot userSnapshot: snapshot.getChildren()) {
                    Appointment app = userSnapshot.getValue(Appointment.class);
                    appointments.add(app);
                }
                AppointmentListAdapter a = new AppointmentListAdapter(DoctorViewAppointments.this, R.layout.appointment_item_layout, appointments);
                listView.setAdapter(a);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }




}