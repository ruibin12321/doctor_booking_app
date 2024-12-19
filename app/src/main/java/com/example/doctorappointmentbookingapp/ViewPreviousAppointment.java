package com.example.doctorappointmentbookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

public class ViewPreviousAppointment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_previous_appointment);

        ListView listview = (ListView) findViewById(R.id.previousAppointment);

        Patient patient = (Patient) getIntent().getSerializableExtra(MainActivity.EXTRA_MESSAGE);


        Calendar today = Calendar.getInstance();




        /*AppointmentDAO.readAppointment(patient);
        LinkedList<Appointment> allAppointment = AppointmentDAO
        AppointmentDAO.printAppointment(allAppointment);*/

        LinkedList<Appointment> allAppointment = new LinkedList<>();


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Appointments");

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot){
                allAppointment.clear();
                for(DataSnapshot child : snapshot.getChildren()){
                    Appointment appt = child.getValue(Appointment.class);
                    if(appt.getPatientHealthCard() == patient.getHealthCardNumber()){

                        allAppointment.add(appt);
                    }
                }

                PreviousAppointmentAdapter adapter = new PreviousAppointmentAdapter(ViewPreviousAppointment.this, findPreviousAppointment(allAppointment));
                listview.setAdapter(adapter);
                /*if(allAppointment.isEmpty() == true){
                }*/
            }
            @Override
            public void onCancelled(DatabaseError error) {
                allAppointment.clear();
            }
        };
        ref.addValueEventListener(listener);



        //LinkedList<Appointment> previousAppointment = new LinkedList<>();




    }

    public LinkedList<Appointment> findPreviousAppointment(LinkedList<Appointment> allAppointment){
        LinkedList<Appointment> previousAppointment = new LinkedList<>();
        try{
            for (Appointment i : allAppointment){

                Calendar today = Calendar.getInstance();
                int presentHour = today.get(Calendar.HOUR_OF_DAY);

                Date appointmentDate = new SimpleDateFormat(Appointment.DATE_FORMAT).parse(i.getBookingDate());
                Date todayDate = today.getTime();
                if(appointmentDate.before(todayDate)){
                    if(appointmentDate.equals(todayDate)){
                        Log.i(">>>>>>>>>>>", String.valueOf(presentHour));
                        Log.i("<<<<<<<TAG>>>>>>>", i.getBookingTime());
                        if (presentHour > Integer.getInteger(i.getBookingTime())){
                            previousAppointment.add(i);
                        }
                    }

                    else{
                        previousAppointment.add(i);
                    }

                }

            }
        }catch (ParseException e){

        }

        return previousAppointment;
    }
}
