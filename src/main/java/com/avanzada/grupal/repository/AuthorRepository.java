package com.avanzada.grupal.repository;

import com.avanzada.grupal.model.Author;
import com.avanzada.grupal.model.AuthorBooks;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    
   
    List<Author> findByName(@Param("name") String name);
    

    
    

   
    
    
}
