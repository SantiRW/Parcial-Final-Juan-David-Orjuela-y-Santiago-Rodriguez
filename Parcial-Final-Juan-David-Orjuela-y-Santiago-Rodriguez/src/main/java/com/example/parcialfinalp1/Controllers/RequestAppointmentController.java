package com.example.parcialfinalp1.Controllers;

import com.example.parcialfinalp1.Model.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RequestAppointmentController {

    @FXML
    private ComboBox<Doctor> cmbDoctors;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox<String> cmbTime;

    @FXML
    private TextArea txtReason;

    @FXML
    private Label lblMessage;

    private Client currentClient;
    private ArrayList<Doctor> availableDoctors;

    @FXML
    public void initialize() {
        // Inicializar las horas disponibles
        cmbTime.setItems(FXCollections.observableArrayList(
                "08:00 AM", "09:00 AM", "10:00 AM", "11:00 AM",
                "02:00 PM", "03:00 PM", "04:00 PM", "05:00 PM"
        ));

        // Configurar cómo se muestran los doctores en el ComboBox
        cmbDoctors.setCellFactory(param -> new ListCell<Doctor>() {
            @Override
            protected void updateItem(Doctor doctor, boolean empty) {
                super.updateItem(doctor, empty);
                if (empty || doctor == null) {
                    setText(null);
                } else {
                    setText(doctor.getName() + " - " + doctor.getSpeciality());
                }
            }
        });

        cmbDoctors.setButtonCell(new ListCell<Doctor>() {
            @Override
            protected void updateItem(Doctor doctor, boolean empty) {
                super.updateItem(doctor, empty);
                if (empty || doctor == null) {
                    setText(null);
                } else {
                    setText(doctor.getName() + " - " + doctor.getSpeciality());
                }
            }
        });
    }

    public void setClient(Client client) {
        this.currentClient = client;
    }

    public void setAvailableDoctors(ArrayList<Doctor> doctors) {
        this.availableDoctors = doctors;
        cmbDoctors.setItems(FXCollections.observableArrayList(doctors));
    }

    @FXML
    private void onSubmit() {
        // Validar campos
        if (cmbDoctors.getValue() == null) {
            showMessage("Por favor selecciona un doctor", false);
            return;
        }

        if (datePicker.getValue() == null) {
            showMessage("Por favor selecciona una fecha", false);
            return;
        }

        if (cmbTime.getValue() == null) {
            showMessage("Por favor selecciona una hora", false);
            return;
        }

        // Crear la cita
        Doctor selectedDoctor = cmbDoctors.getValue();
        LocalDate date = datePicker.getValue();
        String time = cmbTime.getValue();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String appointmentDate = date.format(formatter) + " " + time;

        Appointment newAppointment = new Appointment(appointmentDate, selectedDoctor, currentClient);

        // Agregar la cita al cliente
        if (currentClient != null) {
            currentClient.addAppointment(newAppointment);
        }

        // Agregar la cita al doctor
        selectedDoctor.getAppointment().add(newAppointment);

        // Agregar el cliente a la lista del doctor si no está
        if (!selectedDoctor.getClientList().contains(currentClient)) {
            selectedDoctor.getClientList().add(currentClient);
        }

        showMessage("¡Cita solicitada exitosamente!", true);

        // Limpiar formulario
        clearForm();
    }

    @FXML
    private void onCancel() {
        clearForm();
        lblMessage.setText("");
    }

    private void clearForm() {
        cmbDoctors.setValue(null);
        datePicker.setValue(null);
        cmbTime.setValue(null);
        txtReason.clear();
    }

    private void showMessage(String message, boolean success) {
        lblMessage.setText(message);
        if (success) {
            lblMessage.setStyle("-fx-text-fill: #2ecc71; -fx-font-weight: bold;");
        } else {
            lblMessage.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
        }
    }
}