package com.example.parcialfinalp1;

import com.example.parcialfinalp1.Controllers.UserController;
import com.example.parcialfinalp1.Model.AppData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Crear los datos de la aplicación UNA SOLA VEZ
        AppData appData = new AppData();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Userview.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);

        // Pasar los datos al controlador
        UserController controller = fxmlLoader.getController();
        controller.setAppData(appData);

        stage.setTitle("Sistema de Citas Médicas - Cliente");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}