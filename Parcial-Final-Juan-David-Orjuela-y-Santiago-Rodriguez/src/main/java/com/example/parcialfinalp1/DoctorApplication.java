package com.example.parcialfinalp1;

import com.example.parcialfinalp1.Controllers.DoctorDashboardController;
import com.example.parcialfinalp1.Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DoctorApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Cargar el FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/parcialfinalp1/DoctorDashboard.fxml"));
        Parent root = loader.load();

        // Obtener datos compartidos del DataManager
        AppData data = new AppData();
        Doctor doctor = data.getDoctors().get(0);

        // Configurar el controlador
        DoctorDashboardController controller = loader.getController();
        controller.setDoctor(doctor);

        // Configurar la escena
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Dashboard de Doctor - " + doctor.getName());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}