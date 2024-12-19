package com.example.doctorappointmentbookingapp;

import java.io.Serializable;

public class User implements Serializable {
    protected String username;
    protected String password;
    protected String firstName;
    protected long healthCardNumber;
    protected String lastName;
    protected long phoneNumber;
    private boolean isDoctor;
    protected String email;
    protected String gender;
    protected String dateOfBirth;

    public User() {

    }
    public User(String username, String password, String firstName, String lastName, String email, long healthCardNumber, long phoneNumber, boolean isDoctor, String gender, String dateOfBirth) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.healthCardNumber = healthCardNumber;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.isDoctor = isDoctor;
        this.email = email;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public long getHealthCardNumber() {
        return healthCardNumber;
    }

    public void setHealthCardNumber(long healthCardNumber) {
        this.healthCardNumber = healthCardNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isDoctor() {
        return isDoctor;
    }

    public void setDoctor(boolean doctor) {
        isDoctor = doctor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", healthCardNumber=" + healthCardNumber +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", isDoctor=" + isDoctor +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                '}';
    }



    public String getString() {
        return  "First Name: " + firstName + "\n" +
                "Last Name: " + lastName + "\n" +
                "Gender: " + gender + "\n" +
                "Date of Birth: " + dateOfBirth + "\n" +
                "Phone Number: " + phoneNumber;
    }
}
