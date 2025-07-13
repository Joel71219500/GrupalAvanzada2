package com.avanzada.grupal.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Table("customer")
public class Customer {
    @Id
    private Long id;
    private String name;
    private String email;
    private Integer version;

}
