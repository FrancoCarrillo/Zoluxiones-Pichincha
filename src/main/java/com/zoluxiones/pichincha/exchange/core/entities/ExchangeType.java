package com.zoluxiones.pichincha.exchange.core.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ExchangeType {

    @Id
    private Long id;

    private Double exchangeRate;

    private Long originCurrencyId;

    private Long destinationCurrencyId;

}
