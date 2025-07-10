package com.avanzada.grupal.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.avanzada.grupal.model.Author;
import com.avanzada.grupal.service.AuthorService;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

@Component
public class AuthorController implements Initializable {
  
    @FXML private TextField txtNombre;
    @FXML private TextField txtVersion;
    @FXML private Button btnGuardar;

    @Autowired
    private AuthorService autSer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnGuardar.setOnAction(e -> {
            try {
                // Crear nuevo autor sin establecer el ID (será autoincremental)
                Author author = new Author();
                author.setName(txtNombre.getText());
                author.setVersion(Integer.parseInt(txtVersion.getText()));
                
                // Guardar y obtener el autor con ID generado
                Author savedAuthor = autSer.guardar(author);
                
                // Mostrar el ID generado
                System.out.println("Autor guardado con ID: " + savedAuthor.getId());
                
                // Limpiar formulario
                limpiarFormulario();
                
            } catch (NumberFormatException ex) {
                System.err.println("Error: La versión debe ser un número válido");
            } catch (Exception ex) {
                System.err.println("Error al guardar autor: " + ex.getMessage());
            }
        });
    }
    
    private void limpiarFormulario() {
        txtNombre.clear();
        txtVersion.clear();
    }
    // Aquí puedes agregar más métodos para manejar eventos y lógica del cliente

}
