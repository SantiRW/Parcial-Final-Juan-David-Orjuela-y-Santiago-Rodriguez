package com.example.parcialfinalp1.Controllers;

import com.example.parcialfinalp1.Model.Appointment;
import com.example.parcialfinalp1.Model.Client;
import com.example.parcialfinalp1.Model.Doctor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;

public class ClientDashboardController {

    @FXML
    private Label lblWelcome;

    @FXML
    private Label lblClientName;

    @FXML
    private Label lblClientId;

    @FXML
    private Label lblClientEmail;

    @FXML
    private Label lblAppointmentCount;

    @FXML
    private ListView<Appointment> listViewAppointments;

    private Client currentClient;
    private ObservableList<Appointment> appointmentsList;

    @FXML
    public void initialize() {
        appointmentsList = FXCollections.observableArrayList();
        listViewAppointments.setItems(appointmentsList);

        // Configurar cómo se muestran las citas en la lista
        listViewAppointments.setCellFactory(param -> new ListCell<Appointment>() {
            @Override
            protected void updateItem(Appointment appointment, boolean empty) {
                super.updateItem(appointment, empty);

                if (empty || appointment == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    VBox vbox = new VBox(8);
                    vbox.setStyle("-fx-background-color: #f8f9fa; -fx-background-radius: 8; -fx-padding: 15;");

                    Label lblFecha = new Label("📅 Fecha: " + appointment.getFechaCita());
                    lblFecha.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

                    Label lblDoctor = new Label("👨‍⚕️ Doctor: " +
                            (appointment.getDoctor() != null ? appointment.getDoctor().getName() : "No asignado"));
                    lblDoctor.setStyle("-fx-text-fill: #2c3e50; -fx-font-size: 13px;");

                    Label lblEspecialidad = new Label("🏥 Especialidad: " +
                            (appointment.getDoctor() != null ? appointment.getDoctor().getSpeciality() : "N/A"));
                    lblEspecialidad.setStyle("-fx-text-fill: #7f8c8d; -fx-font-size: 12px;");

                    Label lblEmail = new Label("📧 Email Doctor: " +
                            (appointment.getDoctor() != null ? appointment.getDoctor().getEmail() : "N/A"));
                    lblEmail.setStyle("-fx-text-fill: #7f8c8d; -fx-font-size: 12px;");

                    vbox.getChildren().addAll(lblFecha, lblDoctor, lblEspecialidad, lblEmail);
                    setGraphic(vbox);
                }
            }
        });
    }

    public void setClient(Client client) {
        this.currentClient = client;
        updateClientInfo();
        loadAppointments();
    }

    private void updateClientInfo() {
        if (currentClient != null) {
            lblClientName.setText(currentClient.getName());
            lblClientId.setText(currentClient.getId());
            lblClientEmail.setText(currentClient.getEmail());
        }
    }

    private void loadAppointments() {
        if (currentClient != null) {
            appointmentsList.clear();
            appointmentsList.addAll(currentClient.getAppointmentList());
            lblAppointmentCount.setText("(" + appointmentsList.size() + ")");
        }
    }

    @FXML
    private void onNewAppointment() {
        // Aquí implementarías la lógica para crear una nueva cita
        System.out.println("Crear nueva cita");
    }

    @FXML
    private void onRefresh() {
        loadAppointments();
        System.out.println("Dashboard actualizado");
    }
}