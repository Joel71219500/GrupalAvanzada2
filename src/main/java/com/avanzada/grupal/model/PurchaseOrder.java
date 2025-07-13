package com.avanzada.grupal.model;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.sql.Time;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table("purchaseorder")
public class PurchaseOrder {
    @Id
    private Long id;
    private Long customer_id;
    private LocalDateTime placedon;
    private LocalDateTime deliveredon;
    private Integer status;            //status finalizdo o pendiente o cancelado con numeros 0,1,2
    private BigDecimal total;
}
