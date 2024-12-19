package com.example.doctorappointmentbookingapp;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Doctor extends User {

    private String classification;
    private List<String> availableTimes;
    private List<Patient> patients;
    public final static String[] givenDay = new String[] {" ","Sunday","Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    public final static String[] givenTime = new String[] {" ", "8", "9", "10", "11", "13", "14", "15", "16", "17"};
    private Map<String, Map<String, String>> timeslots;

    public Doctor(){

    }

    public Doctor(String firstName, String lastName, String classification, String email, List<String> availableTimes, long phoneNumber, List<Patient> patients, String username, String password, long healthCardNumber, String dateOfBirth, String gender){
        super(username, password, firstName, lastName, email, healthCardNumber, phoneNumber, true, gender, dateOfBirth);
        this.classification = classification;
        this.availableTimes = availableTimes;
        this.patients = patients;
        this.timeslots = new HashMap<String, Map<String, String>>();
        for (String day : givenDay) {
            HashMap<String, String> timeslot = new HashMap<String, String>();
            this.timeslots.put(day, timeslot);

            for (String hour: givenTime)
                timeslot.put(hour, "");
        }

    }

    public Map<String, Map<String, String>> getTimeslots() {
        return timeslots;
    }

    public void setTimeslots(Map<String, Map<String, String>> timeslots) {
        this.timeslots = timeslots;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public List<String> getAvailableTimes() {
        return availableTimes;
    }

    public void setAvailableTimes(List<String> availableTimes) {
        this.availableTimes = availableTimes;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "classification='" + classification + '\'' +
                ", availableTimes=" + availableTimes +
                ", patients=" + patients +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", healthCardNumber=" + healthCardNumber +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                '}';
    }

    public String availableTimeString(){
        String time = "";
        for(String i : this.availableTimes){
            time = time + i + "\n";
        }
        return time;
    }

    public void addTimeSlot(String patientName, String Date, String Time){
        timeslots.get(Date).put(Time, patientName);
    }
}
