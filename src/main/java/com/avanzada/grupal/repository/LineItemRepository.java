package com.avanzada.grupal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.avanzada.grupal.model.LineItem;

@Repository
public interface LineItemRepository extends CrudRepository<LineItem, Long> {
    
}
