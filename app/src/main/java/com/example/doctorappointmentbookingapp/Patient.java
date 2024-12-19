package com.example.doctorappointmentbookingapp;

public class Patient extends User {

    public Patient(){

    }

    public Patient(String firstName, String username, String password, String lastName, String email, long healthCardNumber, long phoneNumber, String gender, String dateOfBirth) {
        super(username, password, firstName, lastName, email, healthCardNumber, phoneNumber, false, gender, dateOfBirth);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "username='" + username + '\'' +
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
}

