package com.avanzada.grupal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Author {

    @Id
    private long id;
    private String name;
    private String version;

}
