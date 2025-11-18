module com.example.parcialfinalp1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.example.parcialfinalp1 to javafx.fxml;
    exports com.example.parcialfinalp1;
    exports com.example.parcialfinalp1.Controllers;
    opens com.example.parcialfinalp1.Controllers to javafx.fxml;
}