package com.zoluxiones.pichincha.exchange.core.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Exchange {

    @Id
    private Long id;

    private Double originAmount;
    private Double destinationAmount;

    private Long userId;

    private Long exchangeTypeId;

    private LocalDateTime createdDate;
}
