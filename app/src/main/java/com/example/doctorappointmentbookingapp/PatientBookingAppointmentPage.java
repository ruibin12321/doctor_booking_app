package com.example.doctorappointmentbookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class PatientBookingAppointmentPage extends AppCompatActivity {
    String EXTRA_MESSAGE = "doctor_extra";
    private Patient patient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_booking_appointment_page);

        Doctor chosenDoctor = (Doctor) getIntent().getExtras().get("doctor_extra");


        TextView displayName = findViewById(R.id.displayName);
        TextView displayClassification = findViewById(R.id.displayClassification);
        TextView displayTime = findViewById(R.id.displayTime);

        String stringDisplayTime = chosenDoctor.availableTimeString();

        displayName.setText("Name: " + chosenDoctor.getUsername());
        displayClassification.setText("Classification: " + chosenDoctor.getClassification());
        displayTime.setText("Available Time: " + stringDisplayTime);


        Spinner chosenDay = (Spinner) findViewById(R.id.chosenDay);
        Spinner chosenTime = (Spinner) findViewById(R.id.chosenTime);
        ArrayAdapter<String> adapterDay = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, Doctor.givenDay);
        chosenDay.setAdapter(adapterDay);
        chosenDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String SelectDay = (String) adapterView.getItemAtPosition(i);
                List<String> availableHours = new ArrayList<String>();

                Map<String, String> timeslot = chosenDoctor.getTimeslots().get(SelectDay);
                for (String hour : Doctor.givenTime) {
                    if (timeslot.get(hour).equals(""))
                        availableHours.add(hour);
                }
                ArrayAdapter<String> adapterTime = new ArrayAdapter<String>(PatientBookingAppointmentPage.this,android.R.layout.simple_spinner_item, availableHours);
                chosenTime.setAdapter(adapterTime);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        patient = (Patient) getIntent().getSerializableExtra(MainActivity.EXTRA_MESSAGE);


    }

    public void submitAppointment2(View view){
        Doctor chosenDoctor = (Doctor) getIntent().getSerializableExtra("doctor_extra");
        //long patientHC = 903705;
        Spinner chosenDaySpinner = (Spinner) findViewById(R.id.chosenDay);
        Spinner chosenTimeSpinner = (Spinner) findViewById(R.id.chosenTime);

        String chosenDayString = chosenDaySpinner.getSelectedItem().toString();
        String chosenTimeString = chosenTimeSpinner.getSelectedItem().toString();
        Integer bookEnd = Integer.valueOf(chosenTimeString) + 1;
        String bookEndString = String.valueOf(bookEnd);
        long doctorHealthNumber = chosenDoctor.getHealthCardNumber();
        long patientHC = patient.healthCardNumber;

        Calendar calendar = Calendar.getInstance();
        Appointment checkAppointmentDay = new Appointment();
        int todayDay = calendar.get(Calendar.DAY_OF_WEEK);
        int chosenDay = checkAppointmentDay.covertDayToInt(chosenDayString);
        //int chosenDay = Arrays.asList(Doctor.givenDay).indexOf(chosenDayString);
        int offset = ((chosenDay - todayDay) + 7) % 7;
        if(offset == 0){
            int todayTime = calendar.get(Calendar.HOUR_OF_DAY);
            int chosenTime = Integer.parseInt(chosenTimeString);
            if (chosenTime < todayTime) offset = offset + 7;
        }

        calendar.add(Calendar.DATE, offset);


        Appointment storedAppointment = new Appointment(patientHC, doctorHealthNumber, chosenTimeString, chosenDayString,
                DateFormat.format(Appointment.DATE_FORMAT, calendar).toString(), chosenDoctor.getFirstName() + " " + chosenDoctor.getLastName(), patient.getFirstName() + " " + patient.getLastName());

        AppointmentDAO aDAO = new AppointmentDAO();
        aDAO.storeAppointment(storedAppointment);

        chosenDoctor.addTimeSlot(patient.username, chosenDayString, chosenTimeString);
        String key = (String) getIntent().getSerializableExtra("DoctorKey");
        FirebaseDatabase.getInstance().getReference("Doctors").child(key).setValue(chosenDoctor);

        Toast.makeText(this, calendar.getTime().toString(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, PatientDashBoardActivity.class);
        intent.putExtra(MainActivity.EXTRA_MESSAGE, patient);
        startActivity(intent);

    }
}