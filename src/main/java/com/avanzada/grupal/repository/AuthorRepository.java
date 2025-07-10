package com.avanzada.grupal.repository;

import com.avanzada.grupal.model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {

    Author findByName(String name);

    // Puedes agregar más métodos personalizados si es necesario
}
