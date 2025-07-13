package com.avanzada.grupal.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table("inventory")
public class Inventory {
    @Id
    private String bookIsbn;
    private Integer supplied;
    private Integer sold;

}
