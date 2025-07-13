package com.avanzada.grupal.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.avanzada.grupal.model.Book;
@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    
    
    List<Book> findByTitle(String title);
    Book findByIsbn(String isbn);
}
  
 


