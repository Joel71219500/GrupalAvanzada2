package com.avanzada.grupal.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.avanzada.grupal.model.Book;
import com.avanzada.grupal.service.BookService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

@Component
public class BookController implements Initializable {

    @FXML private TextField txtTitulo;
    @FXML private TextField txtIsbn;
    @FXML private TextField txtPrice;
    @FXML private TextField txtVersion;

    @FXML private Button btnGuardar;
    @FXML private Button btnEliminar;
    @FXML private Button btnBuscarTitulo;
    @FXML private Button btnBuscarIsbn;
    @FXML private Button btnListarTodo;

    @FXML private TableView<Book> tableBooks;
    @FXML private TableColumn<Book, String> colIsbn;
    @FXML private TableColumn<Book, String> colTitulo;
    @FXML private TableColumn<Book, BigDecimal> colPrice;
    @FXML private TableColumn<Book, Integer> colVersion;

    @Autowired
    private BookService bookService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarTabla();
        actualizarTablaLibros();

        btnGuardar.setOnAction(e -> guardarLibro());
        btnEliminar.setOnAction(e -> eliminarLibro());
        btnBuscarTitulo.setOnAction(e -> buscarPorTitulo());
        btnBuscarIsbn.setOnAction(e -> buscarPorIsbn());
        btnListarTodo.setOnAction(e -> actualizarTablaLibros());
    }

    private void guardarLibro() {
        String titulo = txtTitulo.getText();
        String isbn = txtIsbn.getText();
        String precioStr = txtPrice.getText();
        String versionStr = txtVersion.getText();

        if (titulo.isBlank() || isbn.isBlank()) {
            mostrarAlerta("Título e ISBN son obligatorios.");
            return;
        }

        BigDecimal precio = null;
        int version = 1;
        try {
            if (!precioStr.isBlank()) {
                precio = new BigDecimal(precioStr);
            }
            if (!versionStr.isBlank()) {
                version = Integer.parseInt(versionStr);
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Precio o versión no tienen un formato válido.");
            return;
        }

        Book nuevo = new Book();
        nuevo.setIsbn(isbn);
        nuevo.setTitle(titulo);
        nuevo.setPrice(precio);
        nuevo.setVersion(version);

        try {
            bookService.guardar(nuevo);
            actualizarTablaLibros();
            limpiarFormulario();
        } catch (Exception ex) {
            mostrarAlerta("Error al guardar libro: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void eliminarLibro() {
        Book seleccionado = tableBooks.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Seleccione un libro para eliminar.");
            return;
        }

        try {
            bookService.eliminar(seleccionado.getIsbn());
            actualizarTablaLibros();
        } catch (Exception e) {
            mostrarAlerta("Error al eliminar libro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void buscarPorTitulo() {
        String titulo = txtTitulo.getText();
        List<Book> resultados = bookService.buscarPorTitulo(titulo);

        if (resultados.isEmpty()) {
            mostrarAlerta("No se encontraron libros con ese título.");
            return;
        }

        ObservableList<Book> lista = FXCollections.observableArrayList(resultados);
        tableBooks.setItems(lista);
    }

    private void buscarPorIsbn() {
        String isbn = txtIsbn.getText();
        Book libro = bookService.buscarPorIsbn(isbn);

        if (libro == null) {
            mostrarAlerta("No se encontró ningún libro con ese ISBN.");
            return;
        }

        ObservableList<Book> lista = FXCollections.observableArrayList(libro);
        tableBooks.setItems(lista);
    }

    private void actualizarTablaLibros() {
        Iterable<Book> libros = bookService.listar();
        ObservableList<Book> lista = FXCollections.observableArrayList();
        libros.forEach(lista::add);
        tableBooks.setItems(lista);
    }

    private void configurarTabla() {
        colIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("title"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colVersion.setCellValueFactory(new PropertyValueFactory<>("version"));
    }

    private void limpiarFormulario() {
        txtTitulo.clear();
        txtIsbn.clear();
        txtPrice.clear();
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
