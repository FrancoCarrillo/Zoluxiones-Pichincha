package com.zoluxiones.pichincha.exchange.infraestructure.interfaces;

import com.zoluxiones.pichincha.exchange.api.model.requests.CreateExchangeRequest;
import com.zoluxiones.pichincha.shared.models.response.MessageResponse;
import reactor.core.publisher.Mono;

public interface IExchangeService {

    Mono<MessageResponse> createExchange(CreateExchangeRequest request);
}
