package com.example.doctorappointmentbookingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class AppointmentListAdapter extends ArrayAdapter<Appointment> {
    private Context context;
    private int resource;

    public AppointmentListAdapter(Context context, int resource, List<Appointment> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    private class ViewHolder {
        TextView patient;
        TextView date;
        TextView day;
        TextView time;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String patient = String.valueOf(getItem(position).getPatientHealthCard());
        String date = getItem(position).getBookingDate();
        String day = getItem(position).getBookingDay();
        String time = getItem(position).getBookingTime();


        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, parent, false);
            holder = new ViewHolder();

            holder.patient = (TextView) convertView.findViewById(R.id.patient);
            holder.date = (TextView) convertView.findViewById(R.id.date);
            holder.day = (TextView) convertView.findViewById(R.id.day);
            holder.time = (TextView) convertView.findViewById(R.id.time);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.patient.setText(patient);
        holder.date.setText(date);
        holder.day.setText(day);
        holder.time.setText(time);


        return convertView;
    }

}
