package com.avanzada.grupal.service;

import org.springframework.stereotype.Service;

import com.avanzada.grupal.model.Author;
import com.avanzada.grupal.repository.AuthorRepository;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    public Author guardar(Author author) {
        return authorRepository.save(author);
    }

    public Author listarTodos() {
        return authorRepository.findAll().iterator().next();
    }
    public Author buscarPorNombreAuthor(String name) {
        return authorRepository.findByName(name);
    }



}
