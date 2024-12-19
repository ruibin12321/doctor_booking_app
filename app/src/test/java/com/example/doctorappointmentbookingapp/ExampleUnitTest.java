package com.example.doctorappointmentbookingapp;

import android.content.Intent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import java.util.LinkedList;
import java.util.function.Consumer;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {

    @Mock
    Patient patient;

    @Mock
    Doctor doctor;

    @Mock
    MainActivity view;

    @Mock
    Model model;

    @Captor
    private ArgumentCaptor<Consumer<Patient>> captor;

    @Captor
    private ArgumentCaptor<Consumer<Doctor>> captor2;


    @Test
    public void testPresenter1() {
        // Patient exist ..

        when(view.getUser()).thenReturn("p1");
        when(view.getPass()).thenReturn("123456");

        Presenter presenter = new Presenter(model, view);
        presenter.loginIntoDashBoardAsPatient();

        // verify if model.fetchPatient() is run
        verify(model).fetchPatient(eq("p1"), eq("123456"), captor.capture());

        Consumer<Patient> callback = captor.getValue();

        //Accept the callback from model as valid patient
        callback.accept(patient);

        // verify if view.redirectToPatientPage is run
        verify(view).redirectToPatientPage(patient);

    }



    @Test
    public void testPresenter2() {
        // Patient does not exist..

        when(view.getUser()).thenReturn("p1");
        when(view.getPass()).thenReturn("123456");

        Presenter presenter = new Presenter(model, view);
        presenter.loginIntoDashBoardAsPatient();

        // verify if model.fetchPatient() is run

        verify(model).fetchPatient(eq("p1"), eq("123456"), captor.capture());
        Consumer<Patient> callback = captor.getValue();

        //Accept the callback from model as null (No matching patient)
        callback.accept(null);

        // verify if view.redirectToPatientPage didn't run
        verify(view, times(0)).redirectToPatientPage(patient);
    }

    @Test
    public void testPresenter3() {
        // Doctor exist ..

        when(view.getUser()).thenReturn("p1");
        when(view.getPass()).thenReturn("123456");

        Presenter presenter = new Presenter(model, view);
        presenter.loginIntoDashBoardAsDoctor();

        // verify if model.fetchDoctor() is run
        verify(model).fetchDoctor(eq("p1"), eq("123456"), captor2.capture());

        Consumer<Doctor> callback = captor2.getValue();

        //Accept the callback from model as valid doctor
        callback.accept(doctor);

        // verify if view.redirectToPatientPage is run
        verify(view).redirectToDoctorPage(doctor);

    }

    @Test
    public void testPresenter4() {
        // Doctor exist ..

        when(view.getUser()).thenReturn("p1");
        when(view.getPass()).thenReturn("123456");

        Presenter presenter = new Presenter(model, view);
        presenter.loginIntoDashBoardAsDoctor();

        // verify if model.fetchDoctor() is run
        verify(model).fetchDoctor(eq("p1"), eq("123456"), captor2.capture());

        Consumer<Doctor> callback = captor2.getValue();

        //Accept the callback from model null (No matching doctor)
        callback.accept(null);

        // verify if view.redirectToPatientPage is run
        verify(view, times(0)).redirectToDoctorPage(doctor);

    }
}
