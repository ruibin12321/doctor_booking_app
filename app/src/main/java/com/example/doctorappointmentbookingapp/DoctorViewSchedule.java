package com.example.doctorappointmentbookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DoctorViewSchedule extends AppCompatActivity {

    private Spinner spinner;

    private ListView listview;

    private Doctor doctor;

    private String chosenDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_view_schedule);

        doctor = (Doctor) getIntent().getSerializableExtra(MainActivity.EXTRA_MESSAGE);

        listview = (ListView) findViewById(R.id.listviewSchedule);

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<Object> a = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Doctor.givenDay);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(a);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                chosenDay = (String) parent.getItemAtPosition(pos);
                if (chosenDay.equals(" ")) return;

                List<String> availableTimes = new ArrayList<String>();

                Map<String, String> timeslot = ((Doctor) doctor).getTimeslots().get(chosenDay);
                for (String time : Doctor.givenTime) {

                    if (!timeslot.get(time).equals(""))
                        time += "   Booked by " + timeslot.get(time);

                    availableTimes.add(time);

                }

                ArrayAdapter<String> aa = new ArrayAdapter<String>(DoctorViewSchedule.this, android.R.layout.simple_list_item_1, availableTimes);
                listview.setAdapter(aa);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}