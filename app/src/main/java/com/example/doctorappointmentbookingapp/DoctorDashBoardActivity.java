package com.example.doctorappointmentbookingapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doctorappointmentbookingapp.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DoctorDashBoardActivity extends AppCompatActivity implements View.OnClickListener{


    private ArrayList<String> arrayList = new ArrayList<>();

    private Doctor doctor;

    private List<Appointment> appointments;
    private ListView appointmentsView;

    
    private Button btnPatients, btnAppointments, btnSchedule, btnLogOut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_dash_board);

        doctor = (Doctor) getIntent().getSerializableExtra(MainActivity.EXTRA_MESSAGE);

        String firstname = doctor.firstName;
        String lastname = doctor.lastName;
        String name = firstname + " " + lastname;
        TextView textView = findViewById(R.id.textView23);
        textView.setText(name);




        btnPatients = (Button) findViewById(R.id.btnPatients);
        btnAppointments = (Button) findViewById(R.id.btnUpcomingAppointment);
        btnSchedule = (Button) findViewById(R.id.btnSchedule);
        btnLogOut = (Button) findViewById(R.id.button6);

        btnPatients.setOnClickListener(this);
        btnAppointments.setOnClickListener(this);
        btnSchedule.setOnClickListener(this);
        btnLogOut.setOnClickListener(this);


    }



    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btnPatients:
                intent = new Intent(this, DoctorViewPatients.class);
                intent.putExtra(MainActivity.EXTRA_MESSAGE, doctor);
                startActivity(intent);
                break;
            case R.id.btnSchedule:
                intent = new Intent(this, DoctorViewSchedule.class);
                intent.putExtra(MainActivity.EXTRA_MESSAGE, doctor);
                startActivity(intent);
                break;
            case R.id.btnUpcomingAppointment:
                intent = new Intent(this, DoctorViewAppointments.class);
                intent.putExtra(MainActivity.EXTRA_MESSAGE, doctor);
                startActivity(intent);
                break;
            case R.id.button6:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
        }
    }
}
