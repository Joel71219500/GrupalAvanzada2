package com.avanzada.grupal.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table("books")
public class Book implements Persistable<String> {

    @Id
    private String isbn;
    private String title;
    private BigDecimal price;
    private int version;

    @Transient
    private boolean isNew = true;

    @Override
    public String getId() {
        return this.isbn;
    }

    @Override
    public boolean isNew() {
        return this.isNew;
    }

    public void setNew(boolean isNew) {
        this.isNew = isNew;
    }
}

