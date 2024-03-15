package com.zoluxiones.pichincha.exchange.infraestructure.interfaces;

import com.zoluxiones.pichincha.exchange.api.model.requests.UpdateCreateExchangeTypeRequest;
import com.zoluxiones.pichincha.exchange.core.entities.ExchangeType;
import com.zoluxiones.pichincha.shared.models.response.MessageResponse;
import reactor.core.publisher.Mono;

public interface IExchangeTypeService {

    Mono<ExchangeType> getExchangeTypeById(Long id);

    Mono<MessageResponse> createExchangeType(UpdateCreateExchangeTypeRequest request);

    Mono<MessageResponse> updateExchangeType(UpdateCreateExchangeTypeRequest request, Long exchangeTypeId);
}
