package com.zoluxiones.pichincha.currency_exchange.core.entities;

import lombok.*;

import javax.persistence.*;

@Entity(name = "exchange_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@With
public class ExchangeType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double exchangeRate;

    @ManyToOne
    private Currency originCurrency;

    @ManyToOne
    private Currency destinationCurrency;


}
