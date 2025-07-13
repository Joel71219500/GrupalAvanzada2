package com.avanzada.grupal.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.avanzada.grupal.model.PurchaseOrder;
import com.avanzada.grupal.repository.PurchaseOrderRepository;

@Service
public class PurchaseOrderService {
    
    private final PurchaseOrderRepository pr;

    public PurchaseOrderService(PurchaseOrderRepository pr) {
        this.pr = pr;
    }
    @Transactional(readOnly = true)
    public Iterable<PurchaseOrder> encontrarTodos() {
        return pr.findAll();
    }

    @Transactional(readOnly = true)
    public PurchaseOrder econtrarPorId(Long id) {
        return pr.findById(id).orElse(null);
    }

    @Transactional
    public PurchaseOrder guardar(PurchaseOrder purchaseOrder) {
        return pr.save(purchaseOrder);
    }

    @Transactional
    public void deleteById(Long id) {
        pr.deleteById(id);
    }
    //metodo buscar por fecha de colocacion

    //mnetodo buscar por rango de fechas placeon

    //actulozar compra             //status finalizdo o pendiente o cancelado con numeros 0,1,2

    @Transactional
    public void actualizar(Long id, int status) {
         pr.updateStatus(id, status);
         System.out.println("Compra actualizada con éxito");
    }
}