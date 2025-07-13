package com.avanzada.grupal.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.avanzada.grupal.model.Book;
import com.avanzada.grupal.repository.AuthorRepository;
import com.avanzada.grupal.repository.BookRepository;

@Service
public class BookService {
    
    private final BookRepository repo;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository repo) {
        this.repo = repo;
        this.authorRepository = null;
    }

    public Iterable<Book> listar() {
        return repo.findAll();
    }

    public Book guardar(Book b) {
        return repo.save(b);
    }

    public void eliminar(String id) {
        repo.deleteById(Long.parseLong(id));
    }
    
    public List<Book> buscarPorTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            return List.of();
        }
        return repo.findByTitle(titulo);
    }
    
    public Book buscarPorIsbn(String isbn) {
        return repo.findByIsbn(isbn);
    }

}
