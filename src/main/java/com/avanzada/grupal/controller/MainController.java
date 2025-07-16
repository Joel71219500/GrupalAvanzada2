package com.avanzada.grupal.controller;

import javafx.scene.control.TabPane;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;

@Component
public class MainController {

    @FXML
    private Label statusLabel;

    @FXML
    private TabPane tabPane;

    @FXML
    public void initialize() {
        statusLabel.setText("¡Aplicación JavaFX con Spring funcionando correctamente!");

        if (tabPane != null) {
            tabPane.getSelectionModel().select(0);
        }
    }
}
