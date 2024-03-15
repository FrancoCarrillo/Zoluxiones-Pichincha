package com.zoluxiones.pichincha.exchange.api.model.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateCreateExchangeTypeRequest {

    @Min(value = 0, message = "Exchange rate must be greater than 0")
    private Double exchangeRate;
    private Long originCurrencyId;
    private Long destinationCurrencyId;
}
