package com.zoluxiones.pichincha.currency_exchange.infraestructure.interfaces;

import com.zoluxiones.pichincha.currency_exchange.api.model.requests.UpdateCreateExchangeTypeRequest;
import com.zoluxiones.pichincha.currency_exchange.core.entities.ExchangeType;
import com.zoluxiones.pichincha.security.api.model.responses.MessageResponse;

public interface IExchangeTypeService {

    ExchangeType getExchangeTypeById(Long id);
    MessageResponse createExchangeType(UpdateCreateExchangeTypeRequest request);
    MessageResponse updateExchangeType(UpdateCreateExchangeTypeRequest request, Long exchangeTypeId);

}
