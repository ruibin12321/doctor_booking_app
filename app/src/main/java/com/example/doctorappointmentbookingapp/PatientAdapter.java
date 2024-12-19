package com.example.doctorappointmentbookingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class PatientAdapter extends ArrayAdapter<Doctor> {

    public PatientAdapter(@NonNull Context context, @NonNull List<Doctor> objects) {
        super(context, 0, objects);
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Doctor doctor = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.patient_adpter, parent, false);
        }
        TextView DoctorName =(TextView) convertView.findViewById(R.id.PatientviewDoctorName);
        TextView classification =(TextView) convertView.findViewById(R.id.classification);
        TextView availableTime =(TextView) convertView.findViewById(R.id.doctorTime);

        DoctorName.setText("Doctor Name: " + doctor.getFirstName() + " " + doctor.getLastName());
        classification.setText("Doctor Classification: " + doctor.getClassification());
        availableTime.setText("Doctor Available Time: " + doctor.availableTimeString());
        //Log.i("info", doctor.toString());
        return convertView;
    }

}
