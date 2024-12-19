package com.example.doctorappointmentbookingapp;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;

public class AppointmentDAO {

    private LinkedList<Appointment> allAppointments;

    public AppointmentDAO(){
        allAppointments = new LinkedList<Appointment>();
    }

    public void storeAppointment(Appointment storeItem){
        allAppointments.add(storeItem);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("Appointments").push().setValue(storeItem);
    }


    public LinkedList<Appointment> readAppointment(){

        LinkedList<Appointment> list = new LinkedList<Appointment>();


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Appointments");
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot){
                list.clear();
                for(DataSnapshot child : snapshot.getChildren()){
                    Appointment appt = child.getValue(Appointment.class);
                    if(appt.getPatientHealthCard() == 903705){

                        list.add(appt);
                    }
                }
                if(list.isEmpty() == true){

                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                list.clear();
            }
        };
        ref.addValueEventListener(listener);
        return list;
    }


}
