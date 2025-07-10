package com.avanzada.grupal.controller;

import org.springframework.stereotype.Component;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

@Component
public class MainController {

    @FXML
    private Label label;

    @FXML
    public void initialize() {
        label.setText("¡Aplicación JavaFX con Spring funcionando correctamente!");
    }
}
