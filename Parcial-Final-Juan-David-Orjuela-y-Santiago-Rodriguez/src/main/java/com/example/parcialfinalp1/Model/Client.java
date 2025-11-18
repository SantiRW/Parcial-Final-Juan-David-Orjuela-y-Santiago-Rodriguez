package com.example.parcialfinalp1.Model;

import java.util.ArrayList;

public class Client extends Person {
    private ArrayList<Appointment> appointmentList;

    public Client() {
        super();
        this.appointmentList = new ArrayList<>();
    }

    public Client(String name, String id, String email) {
        super(name, id, email);
        this.appointmentList = new ArrayList<>();
    }

    public ArrayList<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(ArrayList<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    public void addAppointment(Appointment appointment) {
        this.appointmentList.add(appointment);
    }
}