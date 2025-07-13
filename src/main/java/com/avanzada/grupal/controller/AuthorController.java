package com.avanzada.grupal.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.avanzada.grupal.model.Author;
import com.avanzada.grupal.service.AuthorService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

@Component
public class AuthorController implements Initializable {
  
    @FXML private TextField txtNombre;
    @FXML private TextField txtVersion;
    @FXML private Button btnGuardar;
    @FXML private TableView<Author> tableAuthors; // En lugar de ScrollPane
    @FXML private TableColumn<Author, Long> colId;
    @FXML private TableColumn<Author, String> colNombre;
    @FXML private TableColumn<Author, Integer> colVersion;

    @Autowired
    private AuthorService autSer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configurar las columnas de la tabla
        configurarTabla();
        
        // Cargar datos iniciales
        actualizarTablaAutores();
        
        btnGuardar.setOnAction(e -> {
            try {
                Author author = new Author();
                author.setName(txtNombre.getText());
                author.setVersion(Integer.parseInt(txtVersion.getText()));
                
                Author savedAuthor = autSer.guardar(author);
                System.out.println("Autor guardado con ID: " + savedAuthor.getId());

                // Actualizar la tabla
                actualizarTablaAutores();
                limpiarFormulario();
                
            } catch (NumberFormatException ex) {
                System.err.println("Error: La versión debe ser un número válido");
            } catch (Exception ex) {
                System.err.println("Error al guardar autor: " + ex.getMessage());
            }
        });
    }
    
    private void configurarTabla() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("name"));
        colVersion.setCellValueFactory(new PropertyValueFactory<>("version"));
    }
    
    private void actualizarTablaAutores() {
        try {
            List<Author> autores = autSer.listarTodos();
            ObservableList<Author> observableAuthors = FXCollections.observableArrayList(autores);
            tableAuthors.setItems(observableAuthors);
        } catch (Exception e) {
            System.err.println("Error al cargar autores: " + e.getMessage());
        }
    }
    
    private void limpiarFormulario() {
        txtNombre.clear();
        txtVersion.clear();
    }
}