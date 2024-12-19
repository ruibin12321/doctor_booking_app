package com.example.doctorappointmentbookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.LinkedList;

public class PatientDashBoardActivity extends AppCompatActivity {
    //public static final String EXTRA_MESSAGE = "com.example.b07project.USER";
    Patient patient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_dash_board);

        patient = (Patient) getIntent().getSerializableExtra(MainActivity.EXTRA_MESSAGE);

        //Format name and info
        String firstname = patient.firstName;
        String lastname = patient.lastName;
        String name = firstname + " " + lastname;
        TextView textView = findViewById(R.id.PatientName);
        textView.setText(name);

        String info = patient.getEmail();
        Long info2 = patient.getPhoneNumber();
        Long info3 = patient.healthCardNumber;


        TextView textView2 = findViewById(R.id.PatientInfo);
        textView2.setText("Email: " + info);
        TextView textView3 = findViewById(R.id.patientInfo3);
        textView3.setText("Phone: " + info2);
        TextView textView4 = findViewById(R.id.patientInfo4);
        textView4.setText("Health card: " + info3);

    }

    public void startBooking(View view){
        Intent intent = new Intent(this, PatientViewAppointmentPage.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(MainActivity.EXTRA_MESSAGE, patient);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void viewAppointments(View view){
        Intent intent = new Intent(this, PatientViewAppointmentActivity.class);
        intent.putExtra(MainActivity.EXTRA_MESSAGE, patient);


        startActivity(intent);
    }

    public void viewPrevious(View view){
        Intent intent = new Intent(this, PatientViewDoctors.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(MainActivity.EXTRA_MESSAGE, patient);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void viewPreviousAppointment(View view){
        Intent intent = new Intent(this, ViewPreviousAppointment.class);
        System.out.println("________________   viewPreviousAppointment");

        intent.putExtra(MainActivity.EXTRA_MESSAGE, patient);

        startActivity(intent);
    }

    public void logout(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}