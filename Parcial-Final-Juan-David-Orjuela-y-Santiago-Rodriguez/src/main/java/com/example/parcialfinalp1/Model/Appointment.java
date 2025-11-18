package com.example.parcialfinalp1.Model;

public class Appointment {
    private String fechaCita;
    private Doctor doctor;
    private Client client;

    public Appointment() {
    }

    public Appointment(String fechaCita, Doctor doctor, Client client) {
        this.fechaCita = fechaCita;
        this.doctor = doctor;
        this.client = client;
    }

    public String getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(String fechaCita) {
        this.fechaCita = fechaCita;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "fechaCita='" + fechaCita + '\'' +
                ", doctor=" + doctor +
                ", client=" + client +
                '}';
    }
}