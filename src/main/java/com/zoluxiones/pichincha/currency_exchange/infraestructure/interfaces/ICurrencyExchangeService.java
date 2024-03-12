package com.zoluxiones.pichincha.currency_exchange.infraestructure.interfaces;

import com.zoluxiones.pichincha.currency_exchange.api.model.requests.CreateCurrencyExchangeRequest;
import com.zoluxiones.pichincha.security.api.model.responses.MessageResponse;

public interface ICurrencyExchangeService {
    MessageResponse createCurrencyExchange(CreateCurrencyExchangeRequest request);

}
