package com.avanzada.grupal.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.avanzada.grupal.model.Customer;
import com.avanzada.grupal.service.CustomerService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

@Component
public class CustomerController implements Initializable {

    @FXML private TextField txtId;
    @FXML private TextField txtNombre;
    @FXML private TextField txtCorreo;
    @FXML private Button btnGuardar;
    @FXML private Button btnEliminar;
    @FXML private Button btnBuscarId;
    @FXML private Button btnBuscarNombre;
    @FXML private Button btnBuscarCorreo;
    @FXML private Button btnListarTodo;
    @FXML private TableView<Customer> tableCustomers;
    @FXML private TableColumn<Customer, Long> colId;
    @FXML private TableColumn<Customer, String> colNombre;
    @FXML private TableColumn<Customer, String> colCorreo;

    @Autowired
    private CustomerService cusSer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarTabla();
        actualizarTablaClientes();

        btnGuardar.setOnAction(e -> guardarCliente());
        btnEliminar.setOnAction(e -> eliminarCliente());
        btnBuscarId.setOnAction(e -> buscarPorId());
        btnBuscarNombre.setOnAction(e -> buscarPorNombre());
        btnBuscarCorreo.setOnAction(e -> buscarPorCorreo());
        btnListarTodo.setOnAction(e -> actualizarTablaClientes());
    }

    private void guardarCliente() {
        String nombre = txtNombre.getText();
        String correo = txtCorreo.getText();

        if (nombre.isBlank() || correo.isBlank()) {
            mostrarAlerta("Nombre y correo son obligatorios.");
            return;
        }

        Customer nuevo = new Customer();
        nuevo.setName(nombre);
        nuevo.setEmail(correo);

        try {
            Customer guardado = cusSer.guardar(nuevo);
            System.out.println("ID generado para el cliente: " + guardado.getId());
            actualizarTablaClientes();
            limpiarFormulario();
        } catch (Exception ex) {
            mostrarAlerta("Error al guardar cliente: " + ex.getMessage());
        }
    }

    private void eliminarCliente() {
        Customer seleccionado = tableCustomers.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Seleccione un cliente para eliminar.");
            return;
        }

        cusSer.eliminar(String.valueOf(seleccionado.getId()));
        actualizarTablaClientes();
    }

    private void buscarPorId() {
        String idText = txtId.getText();
        if (idText == null || idText.isBlank()) {
            mostrarAlerta("Debe ingresar un ID para buscar.");
            return;
        }

        Long id = null;
        try {
            id = Long.parseLong(idText);
        } catch (NumberFormatException e) {
            mostrarAlerta("El ID debe ser un número válido.");
            return;
        }

        Customer cliente = cusSer.buscarPorId(id);
        if (cliente == null) {
            mostrarAlerta("No se encontró ningún cliente con ese ID.");
            return;
        }

        tableCustomers.setItems(FXCollections.observableArrayList(cliente));
    }

    private void buscarPorNombre() {
        String nombre = txtNombre.getText();
        List<Customer> resultados = cusSer.buscarPorNombre(nombre);

        if (resultados == null || resultados.isEmpty()) {
            mostrarAlerta("No se encontraron clientes con ese nombre.");
            return;
        }

        tableCustomers.setItems(FXCollections.observableArrayList(resultados));
    }

    private void buscarPorCorreo() {
        String correo = txtCorreo.getText();
        List<Customer> resultados = cusSer.buscarPorEmail(correo);

        if (resultados == null || resultados.isEmpty()) {
            mostrarAlerta("No se encontraron clientes con ese correo.");
            return;
        }

        tableCustomers.setItems(FXCollections.observableArrayList(resultados));
    }

    private void actualizarTablaClientes() {
        Iterable<Customer> clientes = cusSer.listar();
        ObservableList<Customer> lista = FXCollections.observableArrayList();
        clientes.forEach(lista::add);
        tableCustomers.setItems(lista);
    }

    private void configurarTabla() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void limpiarFormulario() {
        txtId.clear();
        txtNombre.clear();
        txtCorreo.clear();
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}



