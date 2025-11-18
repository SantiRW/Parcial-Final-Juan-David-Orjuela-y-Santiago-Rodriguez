package com.example.parcialfinalp1.Model;

import java.util.ArrayList;

public class Doctor extends Person{
    private Speciality Speciality;
    private ArrayList<Appointment> appointmentList;
    private ArrayList<Client> clientList;

    public Doctor(){
        super();
    }
    public Doctor(String name,String id, String email,Speciality speciality){
        super(name,id,email);
        this.Speciality = speciality;
        this.appointmentList = new ArrayList<>();
        this.clientList = new ArrayList<>();
    }
    public ArrayList<Appointment> getAppointment(){
        return appointmentList;
    }
    public void setAppointment(ArrayList<Appointment> appointment){
        this.appointmentList = appointment;
    }
    public ArrayList<Client> getClientList(){
        return clientList;
    }
    public void setClientList(ArrayList<Client> clientList){
        this.clientList = clientList;
    }
    public Speciality getSpeciality(){
        return Speciality;
    }
    public void setSpeciality(Speciality speciality){
        Speciality = speciality;
    }

    @Override
    public String toString(){
        return "Doctor{" + "Speciality=" + Speciality + '}';
    }
}
