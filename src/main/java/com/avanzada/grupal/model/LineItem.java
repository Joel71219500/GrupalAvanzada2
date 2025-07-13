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
@Table("line_item")
public class LineItem {
    @Id
    private Long idx;
    private Long orderId;
    private String bookIsbn;
    private Integer quantity;

}
