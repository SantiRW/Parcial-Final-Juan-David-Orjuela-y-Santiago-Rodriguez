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
import javafx.scene.layout.VBox;

public class DoctorDashboardController {

    @FXML
    private Label lblWelcome;

    @FXML
    private Label lblDoctorName;

    @FXML
    private Label lblDoctorId;

    @FXML
    private Label lblDoctorEmail;

    @FXML
    private Label lblSpeciality;

    @FXML
    private Label lblTotalAppointments;

    @FXML
    private Label lblTotalPatients;

    @FXML
    private Label lblPendingAppointments;

    @FXML
    private ListView<Appointment> listViewAppointments;

    @FXML
    private ListView<Client> listViewPatients;

    private Doctor currentDoctor;
    private ObservableList<Appointment> appointmentsList;
    private ObservableList<Client> patientsList;

    @FXML
    public void initialize() {
        appointmentsList = FXCollections.observableArrayList();
        patientsList = FXCollections.observableArrayList();

        listViewAppointments.setItems(appointmentsList);
        listViewPatients.setItems(patientsList);

        // Configurar cómo se muestran las citas
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

                    Label lblPaciente = new Label("👤 Paciente: " +
                            (appointment.getClient() != null ? appointment.getClient().getName() : "No asignado"));
                    lblPaciente.setStyle("-fx-text-fill: #2c3e50; -fx-font-size: 13px;");

                    Label lblPacienteId = new Label("🆔 ID Paciente: " +
                            (appointment.getClient() != null ? appointment.getClient().getId() : "N/A"));
                    lblPacienteId.setStyle("-fx-text-fill: #7f8c8d; -fx-font-size: 12px;");

                    Label lblEmail = new Label("📧 Email: " +
                            (appointment.getClient() != null ? appointment.getClient().getEmail() : "N/A"));
                    lblEmail.setStyle("-fx-text-fill: #7f8c8d; -fx-font-size: 12px;");

                    vbox.getChildren().addAll(lblFecha, lblPaciente, lblPacienteId, lblEmail);
                    setGraphic(vbox);
                }
            }
        });

        // Configurar cómo se muestran los pacientes
        listViewPatients.setCellFactory(param -> new ListCell<Client>() {
            @Override
            protected void updateItem(Client client, boolean empty) {
                super.updateItem(client, empty);

                if (empty || client == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    VBox vbox = new VBox(5);
                    vbox.setStyle("-fx-background-color: #ecf0f1; -fx-background-radius: 8; -fx-padding: 10;");

                    Label lblNombre = new Label("👤 " + client.getName());
                    lblNombre.setStyle("-fx-font-weight: bold; -fx-font-size: 13px;");

                    Label lblId = new Label("ID: " + client.getId() + " | Email: " + client.getEmail());
                    lblId.setStyle("-fx-text-fill: #7f8c8d; -fx-font-size: 11px;");

                    vbox.getChildren().addAll(lblNombre, lblId);
                    setGraphic(vbox);
                }
            }
        });
    }

    public void setDoctor(Doctor doctor) {
        this.currentDoctor = doctor;
        updateDoctorInfo();
        loadAppointments();
        loadPatients();
        updateStatistics();
    }

    private void updateDoctorInfo() {
        if (currentDoctor != null) {
            lblDoctorName.setText("Dr. " + currentDoctor.getName());
            lblDoctorId.setText(currentDoctor.getId());
            lblDoctorEmail.setText(currentDoctor.getEmail());
            lblSpeciality.setText(currentDoctor.getSpeciality().toString());
        }
    }

    private void loadAppointments() {
        if (currentDoctor != null) {
            appointmentsList.clear();
            appointmentsList.addAll(currentDoctor.getAppointment());
        }
    }

    private void loadPatients() {
        if (currentDoctor != null) {
            patientsList.clear();
            patientsList.addAll(currentDoctor.getClientList());
        }
    }

    private void updateStatistics() {
        if (currentDoctor != null) {
            lblTotalAppointments.setText(String.valueOf(currentDoctor.getAppointment().size()));
            lblTotalPatients.setText(String.valueOf(currentDoctor.getClientList().size()));
            lblPendingAppointments.setText(String.valueOf(currentDoctor.getAppointment().size()));
        }
    }

    @FXML
    private void onRefresh() {
        loadAppointments();
        loadPatients();
        updateStatistics();
        System.out.println("Dashboard actualizado");
    }
}