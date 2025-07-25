package com.avanzada.grupal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.avanzada.grupal.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByName(String name);

    List<Customer> findByEmail(String email);


  
    

}
