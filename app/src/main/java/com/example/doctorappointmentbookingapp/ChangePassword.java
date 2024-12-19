package com.example.doctorappointmentbookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class ChangePassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
    }

    public void changePassword(View view){
        Intent intent = new Intent(this, MainActivity.class);

        EditText editText1 = (EditText)findViewById(R.id.editTextTextPassword3);
        EditText editText2 = (EditText)findViewById(R.id.editTextTextPassword4);
        EditText editText3 = (EditText)findViewById(R.id.editTextNumber);
        EditText editText4 = (EditText)findViewById(R.id.editTextTextPersonName12);

        String password = editText1.getText().toString();
        String password2 = editText2.getText().toString();
        long healthCardNumber = Long.parseLong(editText3.getText().toString());

        if (password.equals(password2)){
            DatabaseReference dbRef = FirebaseDatabase.getInstance("https://doctor-database-8c228-default-rtdb.firebaseio.com/").getReference("Patient"); //Place in the link to the patient database.
            DatabaseReference dbRef2 = FirebaseDatabase.getInstance("https://doctor-database-8c228-default-rtdb.firebaseio.com/").getReference("Doctor"); //Place in the link to the doctor database.
            if (editText4.getText().toString().equalsIgnoreCase("Yes")){
                ValueEventListener valEventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        for (DataSnapshot child : snapshot.getChildren()){
                            if(child != null) {
                                Patient patient = child.getValue(Patient.class);

                                if (patient.healthCardNumber == healthCardNumber) {
                                    patient.setPassword(password);
                                    dbRef.child(Objects.requireNonNull(child.getKey())).setValue(patient);
                                }
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError error) {
                        TextView textView = (TextView)findViewById(R.id.textView14);
                        String message = "There was a problem accessing the database";
                        textView.setText(message);
                    }
                };
                dbRef.addValueEventListener(valEventListener);
            }

            if(editText4.getText().toString().equalsIgnoreCase("No"))  {
                //if the person is a doctor
                ValueEventListener valEventListener2 = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        for (DataSnapshot child : snapshot.getChildren()){
                            Doctor doctor = child.getValue(Doctor.class);
                            if (doctor != null)
                            {
                                if (doctor.healthCardNumber == healthCardNumber){
                                    doctor.setPassword(password);
                                    dbRef2.child(Objects.requireNonNull(child.getKey())).setValue(doctor);
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        TextView textView = (TextView)findViewById(R.id.textView14);
                        String message = "There was a problem accessing the database";
                        textView.setText(message);
                    }
                };
                dbRef2.addValueEventListener(valEventListener2);
            }
        }
    }
}