package com.avanzada.grupal.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.avanzada.grupal.model.Author;
import com.avanzada.grupal.service.AuthorService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

@Component
public class AuthorController implements Initializable {

    @FXML private TextField txtId;
    @FXML private TextField txtNombre;
    @FXML private TextField txtVersion;
    @FXML private Button btnGuardar;
    @FXML private Button btnBuscarId;
    @FXML private Button btnBuscarNombre;
    @FXML private Button btnEliminar;
    @FXML private Button btnListarTodos;
    @FXML private TableView<Author> tableAuthors;
    @FXML private TableColumn<Author, Long>    colId;
    @FXML private TableColumn<Author, String>  colNombre;
    @FXML private TableColumn<Author, Integer> colVersion;

    @Autowired
    private AuthorService autSer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarTabla();
        actualizarTablaAutores();

        btnGuardar.setOnAction(e -> guardarAutor());
        if (btnBuscarId     != null) btnBuscarId.setOnAction(e -> buscarPorId());
        if (btnBuscarNombre != null) btnBuscarNombre.setOnAction(e -> buscarPorNombre());
        if (btnEliminar     != null) btnEliminar.setOnAction(e -> eliminarAutor());
        if (btnListarTodos  != null) btnListarTodos.setOnAction(e -> actualizarTablaAutores());
    }

    private void guardarAutor() {
        try {
            Author author = new Author();
            author.setName(txtNombre.getText());
            author.setVersion(Integer.parseInt(txtVersion.getText()));

            autSer.guardar(author);
            actualizarTablaAutores();
            limpiarFormulario();
        } catch (NumberFormatException ex) {
            mostrarAlerta("La versión debe ser numérica");
        } catch (Exception ex) {
            mostrarAlerta("Error al guardar autor: " + ex.getMessage());
        }
    }

    private void buscarPorId() {
        if (txtId.getText().isBlank()) {
            mostrarAlerta("Ingrese un ID para buscar");
            return;
        }
        try {
            Long id = Long.parseLong(txtId.getText());
            Author autor = autSer.buscarPorId(id);
            if (autor == null) {
                mostrarAlerta("No se encontró autor con ese ID");
            } else {
                tableAuthors.setItems(FXCollections.observableArrayList(autor));
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("ID inválido");
        }
    }

    private void buscarPorNombre() {
        String nombre = txtNombre.getText();
        if (nombre.isBlank()) {
            mostrarAlerta("Ingrese un nombre para buscar");
            return;
        }
        List<Author> encontrados = autSer.buscarPorNombre(nombre);
        if (encontrados.isEmpty()) {
            mostrarAlerta("No se encontraron autores con ese nombre");
        } else {
            tableAuthors.setItems(FXCollections.observableArrayList(encontrados));
        }
    }

    private void eliminarAutor() {
        Author seleccionado = tableAuthors.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Seleccione un autor en la tabla para eliminar");
            return;
        }
        autSer.eliminar(seleccionado.getId());
        actualizarTablaAutores();
    }

    private void configurarTabla() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("name"));
        colVersion.setCellValueFactory(new PropertyValueFactory<>("version"));
    }

    private void actualizarTablaAutores() {
        List<Author> autores = autSer.listarTodos();
        tableAuthors.setItems(FXCollections.observableArrayList(autores));
    }

    private void limpiarFormulario() {
        if (txtId != null) txtId.clear();
        txtNombre.clear();
        txtVersion.clear();
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
