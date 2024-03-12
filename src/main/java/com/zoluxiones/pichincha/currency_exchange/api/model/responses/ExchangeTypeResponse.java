package com.zoluxiones.pichincha.currency_exchange.api.model.responses;

import com.zoluxiones.pichincha.currency_exchange.core.entities.Currency;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ExchangeTypeResponse {
    private Long id;
    private Double exchangeRate;
    private Currency originCurrency;
    private Currency destinationCurrency;
}
