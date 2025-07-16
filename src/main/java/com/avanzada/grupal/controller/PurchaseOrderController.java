package com.avanzada.grupal.controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.avanzada.grupal.model.PurchaseOrder;
import com.avanzada.grupal.service.PurchaseOrderService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

@Component
public class PurchaseOrderController implements Initializable {

    @FXML private TextField txtId;
    @FXML private TextField txtCustomerId;
    @FXML private TextField txtTotal;
    @FXML private ComboBox<String> cbStatus;

    @FXML private Button btnGuardar;
    @FXML private Button btnEliminar;
    @FXML private Button btnBuscarId;
    @FXML private Button btnActualizarEstado;
    @FXML private Button btnListarTodo;

    @FXML private TableView<PurchaseOrder> tableOrders;
    @FXML private TableColumn<PurchaseOrder, Long> colId;
    @FXML private TableColumn<PurchaseOrder, Long> colCustomerId;
    @FXML private TableColumn<PurchaseOrder, LocalDateTime> colPlacedOn;
    @FXML private TableColumn<PurchaseOrder, LocalDateTime> colDeliveredOn;
    @FXML private TableColumn<PurchaseOrder, Integer> colStatus;
    @FXML private TableColumn<PurchaseOrder, String> colTotal;

    @Autowired
    private PurchaseOrderService orderService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbStatus.getItems().addAll("Pendiente", "Finalizado", "Cancelado");
        cbStatus.getSelectionModel().selectFirst();

        configurarTabla();
        actualizarTabla();

        btnGuardar.setOnAction(e -> guardarOrden());
        btnEliminar.setOnAction(e -> eliminarOrden());
        btnBuscarId.setOnAction(e -> buscarPorId());
        btnActualizarEstado.setOnAction(e -> actualizarEstado());
        btnListarTodo.setOnAction(e -> actualizarTabla());
    }

    private void guardarOrden() {
        try {
            Long customerId = Long.parseLong(txtCustomerId.getText());
            PurchaseOrder po = new PurchaseOrder();
            po.setCustomer_id(customerId);
            po.setPlaced_on(LocalDateTime.now());
            po.setDelivered_on(null);
            po.setStatus(cbStatus.getSelectionModel().getSelectedIndex());
            po.setTotal(new java.math.BigDecimal(txtTotal.getText()));

            orderService.guardar(po);
            actualizarTabla();
            limpiarFormulario();
        } catch (Exception ex) {
            mostrarAlerta("Error al guardar orden: " + ex.getMessage());
        }
    }

    private void eliminarOrden() {
        PurchaseOrder selected = tableOrders.getSelectionModel().getSelectedItem();
        if (selected == null) {
            mostrarAlerta("Seleccione una orden para eliminar.");
            return;
        }
        orderService.deleteById(selected.getId());
        actualizarTabla();
    }

    private void buscarPorId() {
        try {
            Long id = Long.parseLong(txtId.getText());
            PurchaseOrder po = orderService.econtrarPorId(id);
            if (po == null) {
                mostrarAlerta("No se encontró una orden con ese ID.");
                return;
            }
            tableOrders.setItems(FXCollections.observableArrayList(po));
        } catch (NumberFormatException e) {
            mostrarAlerta("ID inválido.");
        }
    }

    private void actualizarEstado() {
        try {
            Long id = Long.parseLong(txtId.getText());
            int status = cbStatus.getSelectionModel().getSelectedIndex();
            orderService.actualizar(id, status);
            actualizarTabla();
        } catch (Exception e) {
            mostrarAlerta("Error al actualizar estado: " + e.getMessage());
        }
    }

    private void actualizarTabla() {
        Iterable<PurchaseOrder> orders = orderService.encontrarTodos();
        ObservableList<PurchaseOrder> lista = FXCollections.observableArrayList();
        orders.forEach(lista::add);
        tableOrders.setItems(lista);
    }

    private void configurarTabla() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        colPlacedOn.setCellValueFactory(new PropertyValueFactory<>("placed_on"));
        colDeliveredOn.setCellValueFactory(new PropertyValueFactory<>("delivered_on"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        colStatus.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Integer status, boolean empty) {
                super.updateItem(status, empty);
                if (empty || status == null) {
                    setText(null);
                } else {
                    String estadoTexto = switch (status) {
                        case 0 -> "Pendiente";
                        case 1 -> "Finalizado";
                        case 2 -> "Cancelado";
                        default -> "Desconocido";
                    };
                    setText(estadoTexto);
                }
            }
        });
    }

    private void limpiarFormulario() {
        txtId.clear();
        txtCustomerId.clear();
        txtTotal.clear();
        cbStatus.getSelectionModel().selectFirst();
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}

