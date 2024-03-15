package com.zoluxiones.pichincha.exchange.core.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Currency {

    @Id
    private Long id;

    private String name;
}
