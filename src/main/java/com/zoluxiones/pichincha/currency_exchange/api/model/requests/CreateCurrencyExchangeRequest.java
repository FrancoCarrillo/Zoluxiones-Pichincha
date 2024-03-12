package com.zoluxiones.pichincha.currency_exchange.api.model.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateCurrencyExchangeRequest {
    private Double originAmount;
    private Long originCurrencyId;
    private Long destinationCurrencyId;
    private Long userId;

}
