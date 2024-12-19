package com.example.doctorappointmentbookingapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.LinkedList;
import java.util.List;

public class PreviousAppointmentAdapter extends ArrayAdapter<Appointment>{

    public PreviousAppointmentAdapter(Context context, List<Appointment> objects) {
        super(context, 0, objects);
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        Appointment appointment = getItem(position);
        Log.i("_____________", appointment.toString());

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.pervious_appointment_adpter, parent, false);
        }

        TextView tvPatientName = (TextView) convertView.findViewById(R.id.tvPatientName);
        TextView tvDoctorName = (TextView) convertView.findViewById(R.id.tvDoctorName);
        TextView tvTime = (TextView) convertView.findViewById(R.id.tvTime);
        TextView tvPatientHC = (TextView) convertView.findViewById(R.id.tvPatientHC);

        tvPatientName.setText("Patient Name: " + appointment.getPatientName());
        tvDoctorName.setText(("Doctor Health Card Number: " + appointment.getDoctorHealthCard()));
        tvTime.setText("Appointment Start Time: " + appointment.getBookingDate() + "   " + appointment.getDuration());
        tvPatientHC.setText("Your Health Card Number: " + appointment.getPatientHealthCard());

        return convertView;

    }

}
