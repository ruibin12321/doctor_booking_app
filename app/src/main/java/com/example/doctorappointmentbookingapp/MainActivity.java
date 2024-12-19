package com.example.doctorappointmentbookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.b07project.USER";

    private Presenter presenter;

    private EditText usernameEditText;
    private EditText passwordEditText;
    private RadioGroup userTypeRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new Presenter(new Model(), this);

        usernameEditText = (EditText) findViewById(R.id.editTextTextPersonName3);
        passwordEditText = (EditText) findViewById(R.id.editTextTextPassword2);
        userTypeRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);

    }

    public String getUser(){
        String username = usernameEditText.getText().toString().trim();
        return username;
    }
    public String getPass(){
        String password = passwordEditText.getText().toString().trim();
        return password;
    }

    public void handleClick(View view) {
        if (userTypeRadioGroup.getCheckedRadioButtonId() == R.id.radioButtonPatient)
            presenter.loginIntoDashBoardAsPatient();
        else
            presenter.loginIntoDashBoardAsDoctor();
    }

    public void redirectToPatientPage(Patient patient) {
        Intent intent = new Intent(this, PatientDashBoardActivity.class);
        intent.putExtra(MainActivity.EXTRA_MESSAGE, patient);
        this.startActivity(intent);
    }

    public void redirectToDoctorPage(Doctor doctor) {
        Intent intent = new Intent(this, DoctorDashBoardActivity.class);
        intent.putExtra(MainActivity.EXTRA_MESSAGE, doctor);
        this.startActivity(intent);
    }



    public void signUp(View view) {
        Intent intent = new Intent(this, SignUpPage.class);
        startActivity(intent);
    }

    public void changePassword(View view) {
        Intent intent = new Intent(this, ChangePassword.class);
        startActivity(intent);
    }


    public void displayActivity(Intent intent){
        startActivity(intent);
    }

    public void displayMessage(String message){
        TextView textView = (TextView)findViewById(R.id.invalidInput);
        textView.setText(message);
    }


}
