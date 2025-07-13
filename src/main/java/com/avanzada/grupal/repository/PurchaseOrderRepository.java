package com.avanzada.grupal.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.avanzada.grupal.model.PurchaseOrder;

@Repository
public interface PurchaseOrderRepository extends CrudRepository<PurchaseOrder, Long> {


    @Modifying
    @Query("UPDATE purchase_order SET status = :status WHERE id = :id")
    void updateStatus(@Param("id") Long id, @Param("status") int status);
   
    
}
