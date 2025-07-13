package com.avanzada.grupal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthorBooks {

    private Long authorId;
    private String authorName;
    private String bookTitles; // Títulos concatenados con comas
    


}
