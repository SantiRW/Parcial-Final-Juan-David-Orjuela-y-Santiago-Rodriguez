package com.example.parcialfinalp1.Controllers;

import com.example.parcialfinalp1.Model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;

public class UserController {
    @FXML
    private Label welcomeText;

    @FXML
    private AnchorPane paneTransferencia;

    @FXML
    private Button btnAppointmen;

    @FXML
    private Button btnInfo;

    private Client currentClient;
    private ArrayList<Doctor> availableDoctors;

    @FXML
    public void initialize() {
        // Crear datos de ejemplo
        createSampleData();
    }

    private void createSampleData() {
        // Crear cliente
        currentClient = new Client("Juan Pérez", "12345", "juan@email.com");

        // Crear doctores
        availableDoctors = new ArrayList<>();

        Doctor doctor1 = new Doctor("Dra. María González", "DOC001", "maria@hospital.com", Speciality.CARDIOLOGIST);
        Doctor doctor2 = new Doctor("Dr. Carlos Rodríguez", "DOC002", "carlos@hospital.com", Speciality.PEDIATRICIAN);
        Doctor doctor3 = new Doctor("Dra. Ana Martínez", "DOC003", "ana@hospital.com", Speciality.NUTRISIONIST);
        Doctor doctor4 = new Doctor("Dr. Luis Fernández", "DOC004", "luis@hospital.com", Speciality.PLASTIC_SURGERY);

        availableDoctors.add(doctor1);
        availableDoctors.add(doctor2);
        availableDoctors.add(doctor3);
        availableDoctors.add(doctor4);

        // Crear algunas citas de ejemplo para el cliente
        Appointment cita1 = new Appointment("2024-12-20 10:00 AM", doctor1, currentClient);
        Appointment cita2 = new Appointment("2024-12-25 03:00 PM", doctor2, currentClient);

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

        Appointment cita3 = new Appointment("2024-12-21 11:00 AM", doctor1, client2);
        Appointment cita4 = new Appointment("2024-12-22 02:00 PM", doctor3, client3);

        doctor1.getAppointment().add(cita3);
        doctor1.getClientList().add(client2);

        doctor3.getAppointment().add(cita4);
        doctor3.getClientList().add(client3);
    }

    @FXML
    protected void onAppointment() {
        System.out.println("Botón Pedir Cita presionado");
        loadRequestAppointmentView();
    }

    @FXML
    protected void onInfo() {
        System.out.println("Botón Consultar Cita presionado");
        loadClientDashboard();
    }

    private void loadRequestAppointmentView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/parcialfinalp1/RequestAppointment.fxml"));
            Parent appointmentRoot = loader.load();

            // Obtener el controlador y pasarle el cliente y los doctores disponibles
            RequestAppointmentController controller = loader.getController();
            controller.setClient(currentClient);
            controller.setAvailableDoctors(availableDoctors);

            // Limpiar el panel y agregar la vista
            paneTransferencia.getChildren().clear();
            AnchorPane.setTopAnchor(appointmentRoot, 0.0);
            AnchorPane.setBottomAnchor(appointmentRoot, 0.0);
            AnchorPane.setLeftAnchor(appointmentRoot, 0.0);
            AnchorPane.setRightAnchor(appointmentRoot, 0.0);
            paneTransferencia.getChildren().add(appointmentRoot);

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al cargar la vista de solicitud de cita: " + e.getMessage());
        }
    }

    private void loadClientDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/parcialfinalp1/ClientDashboard.fxml"));
            Parent dashboardRoot = loader.load();

            // Obtener el controlador y pasarle el cliente
            ClientDashboardController controller = loader.getController();
            controller.setClient(currentClient);

            // Limpiar el panel y agregar el dashboard
            paneTransferencia.getChildren().clear();
            AnchorPane.setTopAnchor(dashboardRoot, 0.0);
            AnchorPane.setBottomAnchor(dashboardRoot, 0.0);
            AnchorPane.setLeftAnchor(dashboardRoot, 0.0);
            AnchorPane.setRightAnchor(dashboardRoot, 0.0);
            paneTransferencia.getChildren().add(dashboardRoot);

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al cargar el dashboard del cliente: " + e.getMessage());
        }
    }

    // Métodos para los efectos hover
    @FXML
    protected void hoverOn() {
        btnAppointmen.setStyle("-fx-background-color: #2980b9; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-cursor: hand; -fx-background-radius: 12;");
    }

    @FXML
    protected void hoverOff() {
        btnAppointmen.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-cursor: hand; -fx-background-radius: 12;");
    }

    @FXML
    protected void hoverOn2() {
        btnInfo.setStyle("-fx-background-color: #2980b9; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-cursor: hand; -fx-background-radius: 12;");
    }

    @FXML
    protected void hoverOff2() {
        btnInfo.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-cursor: hand; -fx-background-radius: 12;");
    }
}
