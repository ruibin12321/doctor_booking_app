package com.example.doctorappointmentbookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.doctorappointmentbookingapp.Doctor;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.*;
/*
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 */

public class SignUpPage extends AppCompatActivity {
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        //Log.i("msg", "onCreate: ");
    }

    public void addUser(View view){
        //System.out.println("You are clicked! ");

        Intent intent = new Intent(this, MainActivity.class);

        EditText editText = (EditText)findViewById(R.id.editTextTextPersonName);
        String firstName = editText.getText().toString();

        EditText editText2 = (EditText)findViewById(R.id.editTextTextPersonName2);
        String lastName = editText2.getText().toString();

        EditText editText3 = (EditText)findViewById(R.id.editTextTextPersonName6);
        long healthCardNumber = Long.parseLong(editText3.getText().toString());

        EditText editText4 = (EditText)findViewById(R.id.editTextTextPersonName4);
        String email = editText4.getText().toString();

        EditText editText5 = (EditText)findViewById(R.id.editTextTextPersonName7);
        long phoneNumber = Long.parseLong(editText5.getText().toString());

        EditText editText6 = (EditText)findViewById(R.id.editTextTextPersonName9);
        EditText editText7 = (EditText)findViewById(R.id.editTextTextPersonName10);

        boolean isDoctor = false;
        LinkedList<String> availableTimes = new LinkedList<String>();
        String classification = null;

        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup2);
        int id = radioGroup.getCheckedRadioButtonId();
        RadioButton isDoctorRadioButton = (RadioButton)findViewById(id);

        if (isDoctorRadioButton.getText().toString().equals("Yes")){
            isDoctor = true;
            classification = editText6.getText().toString();
            Pattern pattern = Pattern.compile("((\\s?(Monday|Tuesday|Wednesday|Thursday|Friday|Saturday|Sunday)+\\s?(2[0-3]|[01]?[0-9]):([0-5]?[0-9]))+,?)+");
            Matcher matcher = pattern.matcher(editText7.getText().toString().trim());
            Boolean matches = matcher.matches();
            String timelist = matcher.group();
            String[] availableT = timelist.split(",");
            availableTimes = new LinkedList<String>();
            Collections.addAll(availableTimes, availableT);
        }
        else if (isDoctorRadioButton.getText().toString().equals("No")){
            isDoctor = false;

        }

        EditText editText8 = (EditText)findViewById(R.id.editTextTextPersonName8);
        String username = editText8.getText().toString();
        EditText editText9 = (EditText)findViewById(R.id.editTextTextPassword);
        String password = editText9.getText().toString();
        EditText editText10 = (EditText)findViewById(R.id.editTextTextPersonName49);
        String gender = editText10.getText().toString();
        EditText editText11 = (EditText)findViewById(R.id.editTextTextPersonName13);
        String dateOfBirth = editText11.getText().toString();


        if (isDoctor) {
            user = new Doctor(firstName, lastName, classification, email, availableTimes, phoneNumber, null, username, password, healthCardNumber, dateOfBirth, gender);
            user.setDoctor(true);
            DatabaseReference dbRef = FirebaseDatabase.getInstance("https://doctor-database-8c228-default-rtdb.firebaseio.com/").getReference("Doctors");
            //dbRef.setValue(user);
            dbRef.push().setValue(user, (databaseError, databaseReference) -> Toast.makeText(SignUpPage.this,"Adding you to the database",Toast.LENGTH_LONG).show());
        }
        else {
            user = new Patient(firstName, username, password, lastName, email, healthCardNumber, phoneNumber, gender, dateOfBirth);
            user.setDoctor(false);
            DatabaseReference dbRef = FirebaseDatabase.getInstance("https://doctor-database-8c228-default-rtdb.firebaseio.com/").getReference("patients");
            dbRef.push().setValue(user, (databaseError, databaseReference) -> Toast.makeText(SignUpPage.this,"Adding you to the database",Toast.LENGTH_LONG).show());

        }
        startActivity(intent);
    }

    public void onIsDoctorClick(View view){
        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup2);
        int id = radioGroup.getCheckedRadioButtonId();
        RadioButton isDoctorRadioButton = (RadioButton)findViewById(id);
        EditText editText9 = (EditText)findViewById(R.id.editTextTextPersonName9);
        EditText editText10 = (EditText)findViewById(R.id.editTextTextPersonName10);

        if (isDoctorRadioButton.getText().toString().equals("Yes")){
            editText9.setEnabled(true);
            editText10.setEnabled(true);
        }
        else{
            editText9.setEnabled(false);
            editText10.setEnabled(false);
        }
    }
}
