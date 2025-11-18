package com.example.parcialfinalp1.Controllers;

import com.example.parcialfinalp1.Model.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class UserController {
    @FXML
    private Label lblMessage;

    @FXML
    private Label lblUserRole;

    @FXML
    private Label lblUserName;

    @FXML
    private Label lblCurrentUser;

    @FXML
    private Label lblUserId;

    @FXML
    private AnchorPane paneTransferencia;

    @FXML
    private ComboBox<String> cmbRole;

    @FXML
    private VBox clientMenu;

    @FXML
    private VBox doctorMenu;

    @FXML
    private ComboBox<Doctor> cmbDoctorSelect;

    @FXML
    private Button btnAppointmen;

    @FXML
    private Button btnInfo;

    @FXML
    private Button btnDoctorDashboard;

    private AppData appData; // En lugar de usar Singleton
    private Client currentClient;
    private ArrayList<Doctor> availableDoctors;
    private Doctor selectedDoctor;

    // Método para recibir los datos desde HelloApplication
    public void setAppData(AppData appData) {
        this.appData = appData;
        this.currentClient = appData.getCurrentClient();
        this.availableDoctors = appData.getDoctors();
        initialize();
    }

    private void initialize() {
        // Configurar ComboBox de roles
        cmbRole.setItems(FXCollections.observableArrayList("Cliente", "Doctor"));
        cmbRole.setValue("Cliente");

        // Configurar ComboBox de doctores
        cmbDoctorSelect.setItems(FXCollections.observableArrayList(availableDoctors));

        // Configurar cómo se muestran los doctores en el ComboBox
        cmbDoctorSelect.setCellFactory(param -> new ListCell<Doctor>() {
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

        cmbDoctorSelect.setButtonCell(new ListCell<Doctor>() {
            @Override
            protected void updateItem(Doctor doctor, boolean empty) {
                super.updateItem(doctor, empty);
                if (empty || doctor == null) {
                    setText(null);
                } else {
                    setText(doctor.getName());
                }
            }
        });

        // Seleccionar el primer doctor por defecto
        if (!availableDoctors.isEmpty()) {
            selectedDoctor = availableDoctors.get(0);
            cmbDoctorSelect.setValue(selectedDoctor);
        }

        // Actualizar la información del usuario (Cliente por defecto)
        updateUserInfo();
    }

    private void updateUserInfo() {
        String selectedRole = cmbRole.getValue();

        if ("Cliente".equals(selectedRole)) {
            lblUserRole.setText("Rol: 👤 Cliente");
            lblUserName.setText("Usuario: " + currentClient.getName());
            lblCurrentUser.setText("👤 " + currentClient.getName());
            lblUserId.setText(currentClient.getId());
            lblMessage.setText("Bienvenido, " + currentClient.getName());
        } else if ("Doctor".equals(selectedRole) && selectedDoctor != null) {
            lblUserRole.setText("Rol: 👨‍⚕️ Doctor");
            lblUserName.setText("Doctor: " + selectedDoctor.getName());
            lblCurrentUser.setText("👨‍⚕️ Dr. " + selectedDoctor.getName());
            lblUserId.setText(selectedDoctor.getId());
            lblMessage.setText("Bienvenido, Dr. " + selectedDoctor.getName());
        }
    }

    @FXML
    protected void onRoleChanged() {
        String selectedRole = cmbRole.getValue();
        if ("Cliente".equals(selectedRole)) {
            clientMenu.setVisible(true);
            doctorMenu.setVisible(false);
            updateUserInfo();
            clearPanel();
        } else if ("Doctor".equals(selectedRole)) {
            clientMenu.setVisible(false);
            doctorMenu.setVisible(true);
            updateUserInfo();
            clearPanel();
        }
    }

    @FXML
    protected void onDoctorSelected() {
        selectedDoctor = cmbDoctorSelect.getValue();
        updateUserInfo();
        System.out.println("Doctor seleccionado: " + (selectedDoctor != null ? selectedDoctor.getName() : "Ninguno"));
    }

    @FXML
    protected void onDoctorDashboard() {
        if (selectedDoctor == null) {
            System.out.println("Por favor selecciona un doctor");
            return;
        }
        System.out.println("Cargando dashboard del doctor: " + selectedDoctor.getName());
        loadDoctorDashboard();
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

            RequestAppointmentController controller = loader.getController();
            controller.setClient(currentClient);
            controller.setAvailableDoctors(availableDoctors);
            controller.setParentController(this);

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

            ClientDashboardController controller = loader.getController();
            controller.setClient(currentClient); // Usar el cliente actual (ya tiene todas las citas actualizadas)

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

    private void loadDoctorDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/parcialfinalp1/DoctorDashboard.fxml"));
            Parent dashboardRoot = loader.load();

            DoctorDashboardController controller = loader.getController();
            controller.setDoctor(selectedDoctor); // El doctor ya tiene todas las citas actualizadas

            paneTransferencia.getChildren().clear();
            AnchorPane.setTopAnchor(dashboardRoot, 0.0);
            AnchorPane.setBottomAnchor(dashboardRoot, 0.0);
            AnchorPane.setLeftAnchor(dashboardRoot, 0.0);
            AnchorPane.setRightAnchor(dashboardRoot, 0.0);
            paneTransferencia.getChildren().add(dashboardRoot);

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al cargar el dashboard del doctor: " + e.getMessage());
        }
    }

    private void clearPanel() {
        paneTransferencia.getChildren().clear();
        VBox vbox = new VBox(10);
        vbox.setAlignment(javafx.geometry.Pos.CENTER);
        vbox.setLayoutX(100.0);
        vbox.setLayoutY(220.0);

        Label emoji = new Label("👋");
        emoji.setStyle("-fx-font-size: 40px;");

        Label message = new Label("Selecciona una opción del menú");
        message.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");

        vbox.getChildren().addAll(emoji, message);
        paneTransferencia.getChildren().add(vbox);
    }

    public void refreshClientDashboard() {
        loadClientDashboard();
    }

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