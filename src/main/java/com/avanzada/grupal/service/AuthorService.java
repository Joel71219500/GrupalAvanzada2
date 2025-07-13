package com.avanzada.grupal.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avanzada.grupal.model.Author;
import com.avanzada.grupal.model.AuthorBooks;
import com.avanzada.grupal.repository.AuthorRepository;

@Service
public class AuthorService {
    @Autowired
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    
    public Author guardar(Author author) {
        return authorRepository.save(author);
    }

    public List<Author> listarTodos() {
        return (List<Author>) authorRepository.findAll();
    }
    
    public Author buscarPorId(Long id) {
        return authorRepository.findById(id).orElse(null);
    }
    
    public void eliminar(Long id) {
        authorRepository.deleteById(id);
    }

    public List<Author> buscarPorNombre(String nombre) {
        return authorRepository.findByName(nombre);
    }

    }
 

    
 

