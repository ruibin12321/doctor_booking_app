package com.example.doctorappointmentbookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PatientViewAppointmentPage extends AppCompatActivity {
    String EXTRA_MESSAGE = "doctor_extra";
    private List<Doctor> allDoctor;
    private ListView listview;
    private List<String> allDoctorKey;
    private Doctor filter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_view_appointment_page);

        filter = new Doctor();
        filter.gender = "A";
        filter.setClassification("");

        allDoctor = new ArrayList<Doctor>();
        allDoctorKey = new ArrayList<String>();


        listview = (ListView) findViewById(R.id.doctorAppointmentList);



        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(PatientViewAppointmentPage.this, PatientBookingAppointmentPage.class);

                Doctor ChosenDoctor = (Doctor) listview.getItemAtPosition(position);
                Log.i("textTag", ChosenDoctor.toString());
                intent.putExtra("doctor_extra", ChosenDoctor);
                intent.putExtra("DoctorKey", allDoctorKey.get(position));
                intent.putExtra(MainActivity.EXTRA_MESSAGE, getIntent().getSerializableExtra(MainActivity.EXTRA_MESSAGE));


                /*Bundle bundle = new Bundle();
                bundle.putSerializable(EXTRA_MESSAGE, ChosenDoctor);
                intent.putExtras(bundle);*/
                startActivity(intent);

            }
        });

        getDoctors();
    }

    public void getDoctors(){
        FirebaseDatabase.getInstance().getReference("Doctors").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                allDoctor.clear();
                allDoctorKey.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    Doctor getDoctor = snapshot1.getValue(Doctor.class);
                    Log.i("***********", getDoctor.toString());
                    allDoctor.add(getDoctor);
                    allDoctorKey.add(snapshot1.getKey());
                }
                PatientAdapter adapter = new PatientAdapter(PatientViewAppointmentPage.this, allDoctor);
                listview.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void updateFilters(View view){
        EditText editText = (EditText) findViewById(R.id.editTextTextPersonName5);
        filter.setClassification(editText.getText().toString().trim());

        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup3);

        if (radioGroup.getCheckedRadioButtonId() == R.id.radioButton3){
            filter.setGender("M");
        }
        else if(radioGroup.getCheckedRadioButtonId() == R.id.radioButton4){
            filter.setGender("F");
        }
        else{
            filter.setGender("A");
        }


        FirebaseDatabase.getInstance().getReference("Doctors").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                allDoctor.clear();
                allDoctorKey.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    Doctor getDoctor = snapshot1.getValue(Doctor.class);
                    Log.i("***********", getDoctor.toString());
                    if((filter.getGender().equals("A") || filter.getGender().equals(getDoctor.getGender())) && (filter.getClassification().equals("") || filter.getClassification().equals(getDoctor.getClassification()))){
                        allDoctor.add(getDoctor);
                        allDoctorKey.add(snapshot1.getKey());
                    }

                }
                PatientAdapter adapter = new PatientAdapter(PatientViewAppointmentPage.this, allDoctor);
                listview.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void resetFilters(View view){
        filter.gender = "A";
        filter.setClassification("Doctor");

        RadioButton r1 = (RadioButton)findViewById(R.id.radioButton3);
        r1.setChecked(false);
        RadioButton r2 = (RadioButton)findViewById(R.id.radioButton4);
        r2.setChecked(false);

        EditText editText = (EditText) findViewById(R.id.editTextTextPersonName5);
        editText.setText("");

        getDoctors();
    }
}