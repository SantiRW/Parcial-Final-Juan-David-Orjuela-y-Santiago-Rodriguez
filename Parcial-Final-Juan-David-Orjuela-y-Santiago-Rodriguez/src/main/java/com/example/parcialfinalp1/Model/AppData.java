package com.example.parcialfinalp1.Model;

import java.util.ArrayList;

public class AppData {
    private Client currentClient;
    private ArrayList<Doctor> doctors;

    public AppData() {
        doctors = new ArrayList<>();
        initializeData();
    }

    private void initializeData() {
        // Crear cliente
        currentClient = new Client("Juan Pérez", "12345", "juan@email.com");

        // Crear doctores
        Doctor doctor1 = new Doctor("Dra. María González", "DOC001", "maria@hospital.com", Speciality.CARDIOLOGIST);
        Doctor doctor2 = new Doctor("Dr. Carlos Rodríguez", "DOC002", "carlos@hospital.com", Speciality.PEDIATRICIAN);
        Doctor doctor3 = new Doctor("Dra. Ana Martínez", "DOC003", "ana@hospital.com", Speciality.NUTRISIONIST);
        Doctor doctor4 = new Doctor("Dr. Luis Fernández", "DOC004", "luis@hospital.com", Speciality.PLASTIC_SURGERY);

        doctors.add(doctor1);
        doctors.add(doctor2);
        doctors.add(doctor3);
        doctors.add(doctor4);

        // Crear algunas citas de ejemplo para el cliente CON DESCRIPCIÓN
        Appointment cita1 = new Appointment("2024-12-20 10:00 AM", doctor1, currentClient,
                "Chequeo general de salud cardiovascular. Seguimiento de presión arterial.");
        Appointment cita2 = new Appointment("2024-12-25 03:00 PM", doctor2, currentClient,
                "Control de rutina anual. Vacunación pendiente.");

        currentClient.addAppointment(cita1);
        currentClient.addAppointment(cita2);

        // Agregar citas a los doctores
        doctor1.getAppointment().add(cita1);
        doctor2.getAppointment().add(cita2);

        doctor1.getClientList().add(currentClient);
        doctor2.getClientList().add(currentClient);

        // Crear algunos pacientes para los doctores
        Client client2 = new Client("María López", "54321", "maria@email.com");
        Client client3 = new Client("Pedro Sánchez", "98765", "pedro@email.com");

        Appointment cita3 = new Appointment("2024-12-21 11:00 AM", doctor1, client2,
                "Consulta por dolor en el pecho y dificultad para respirar.");
        Appointment cita4 = new Appointment("2024-12-22 02:00 PM", doctor3, client3,
                "Plan nutricional personalizado para bajar de peso.");

        doctor1.getAppointment().add(cita3);
        doctor1.getClientList().add(client2);

        doctor3.getAppointment().add(cita4);
        doctor3.getClientList().add(client3);
    }

    public Client getCurrentClient() {
        return currentClient;
    }

    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }
}