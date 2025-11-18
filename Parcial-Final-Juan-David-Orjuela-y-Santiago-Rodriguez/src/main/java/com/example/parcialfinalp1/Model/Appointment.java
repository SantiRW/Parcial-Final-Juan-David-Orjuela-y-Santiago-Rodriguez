package com.example.parcialfinalp1.Model;

public class Appointment {
    private String fechaCita;
    private Doctor doctor;
    private Client client;
    private String descripcion; // Nueva propiedad

    public Appointment() {
    }

    public Appointment(String fechaCita, Doctor doctor, Client client) {
        this.fechaCita = fechaCita;
        this.doctor = doctor;
        this.client = client;
        this.descripcion = "";
    }

    public Appointment(String fechaCita, Doctor doctor, Client client, String descripcion) {
        this.fechaCita = fechaCita;
        this.doctor = doctor;
        this.client = client;
        this.descripcion = descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "fechaCita='" + fechaCita + '\'' +
                ", doctor=" + doctor +
                ", client=" + client +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}