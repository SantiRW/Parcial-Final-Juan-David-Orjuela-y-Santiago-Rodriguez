package com.example.parcialfinalp1.Model;

public class Appointment {
    private String fechaCita;
    private Doctor doctor;
    private Client client;

    public Appointment() {
    }

    public Appointment(String fechaCita, String doctor) {
        this.fechaCita = fechaCita;
    }
    public String getFechaCita() {
        return fechaCita;
    }
    public void setFechaCita(String fechaCita) {
        this.fechaCita = fechaCita;
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
