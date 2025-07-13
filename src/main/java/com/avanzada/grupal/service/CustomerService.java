package com.avanzada.grupal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.avanzada.grupal.model.Customer;
import com.avanzada.grupal.repository.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository repo;

    public CustomerService(CustomerRepository repo) {
        this.repo = repo;
    }

    public Iterable<Customer> listar() {
        return repo.findAll();
    }

    public Customer guardar(Customer customer) {
        return repo.save(customer);
    }

    public void eliminar(String id) {
        repo.deleteById(Long.parseLong(id));
    }
    
    public Customer buscarPorId(Long id) {
        return repo.findById(id).orElse(null);

    }

    public List<Customer> buscarPorEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            System.out.println("El email no existe o es nulo");

            return null;
        }
        return repo.findByEmail(email);
    }
    public List<Customer> buscarPorNombre(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("El nombre no existe o es nulo");
            return null;
        }
        return repo.findByName(name);
   
    }
}