package com.avanzada.grupal.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table("authors")
public class Author {

    @Id
    private Long id; // Cambiar a Long para permitir null
    private String name;
    private int version;



}
