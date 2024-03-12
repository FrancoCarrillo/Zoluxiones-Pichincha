package com.zoluxiones.pichincha.currency_exchange.api.model.responses;

import com.zoluxiones.pichincha.currency_exchange.core.entities.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExchangeTypeResponse {
    private Long id;
    private Double exchangeRate;
    private Currency originCurrency;
    private Currency destinationCurrency;
}
