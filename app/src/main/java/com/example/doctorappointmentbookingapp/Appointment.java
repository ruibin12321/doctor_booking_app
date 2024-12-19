package com.example.doctorappointmentbookingapp;

public class Appointment {
    private long patientHealthCard;
    private long doctorHealthCard;
    private String doctorName;
    private String patientName;
    private String bookingTime;
    private String bookingDay;
    private String bookingDate;
    public final static String DATE_FORMAT = "yyyy/MM/dd";

    public Appointment(){

    }

    public Appointment(long consPatientHealthCard, long consDoctorHealthCard, String consBookingTime, String endTime, String consBookingDate, String doctorName, String patientName){
        this.patientHealthCard = consPatientHealthCard;
        this.doctorHealthCard = consDoctorHealthCard;
        this.bookingTime = consBookingTime;
        this.bookingDay = endTime;
        this.bookingDate = consBookingDate;
        this.doctorName = doctorName;
        this.patientName = patientName;
    }

    public String getDoctorName(){ return doctorName; }

    public String getPatientName(){ return patientName; }

    public void setDoctorName(String setDoctorName ){ this.doctorName = setDoctorName; }

    public void setPatientName(String setPatientName) {
        this.patientName = setPatientName;
    }

    public long getPatientHealthCard(){
        return this.patientHealthCard;
    }

    public long getDoctorHealthCard(){
        return this.doctorHealthCard;
    }

    public String getBookingTime(){
        return this.bookingTime;
    }

    public String getBookingDate(){
        return this.bookingDate;
    }

    public void setPatientHealthCard(long patientHealthCard){
        this.patientHealthCard = patientHealthCard;
    }

    public void setDoctorHealthCard(long doctorHealthCard){
        this.doctorHealthCard = doctorHealthCard;
    }

    public void setBookingTime(String bookingTime){
        this.bookingTime = bookingTime;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBookingDay() {
        return bookingDay;
    }

    public void setBookingDay(String endTime) {
        this.bookingDay = endTime;
    }

    public String getDuration(){
        if(bookingDay != null){
            return bookingDay + "-" + bookingTime + ":00";
        }
        else{
            return bookingTime;
        }

    }

    public int covertDayToInt(String Day){
        if (Day.equals("Sunday"))
            return 1;
        else if (Day.equals("Monday"))
            return 2;
        else if (Day.equals("Tuesday"))
            return 3;
        else if (Day.equals("Wednesday"))
            return 4;
        else if (Day.equals("Thursday"))
            return 5;
        else if (Day.equals("Friday"))
            return 6;
        return 7;
    }

}
