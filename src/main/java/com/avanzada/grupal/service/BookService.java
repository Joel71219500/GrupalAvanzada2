package com.avanzada.grupal.service;

import java.util.List;

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
        boolean existe = repo.existsById(b.getIsbn());
        b.setNew(!existe);
        Book saved = repo.save(b);
        saved.setNew(false);
        return saved;
    }

    public void eliminar(String isbn) {
        repo.deleteById(isbn);
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

