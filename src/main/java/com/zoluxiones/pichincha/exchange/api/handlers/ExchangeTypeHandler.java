package com.zoluxiones.pichincha.exchange.api.handlers;

import com.zoluxiones.pichincha.exchange.api.model.requests.UpdateCreateExchangeTypeRequest;
import com.zoluxiones.pichincha.exchange.core.entities.ExchangeType;
import com.zoluxiones.pichincha.exchange.infraestructure.interfaces.IExchangeTypeService;
import com.zoluxiones.pichincha.shared.models.response.MessageResponse;
import com.zoluxiones.pichincha.shared.validation.ObjectValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@RequiredArgsConstructor
@Slf4j
public class ExchangeTypeHandler {

    private final IExchangeTypeService exchangeTypeService;
    private final ObjectValidator objectValidator;

    public Mono<ServerResponse> findById(ServerRequest request) {

        Long exchangeTypeId = Long.valueOf(request.pathVariable("id"));
        Mono<ExchangeType> exchangeType = exchangeTypeService.getExchangeTypeById(exchangeTypeId);

        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(exchangeType, ExchangeType.class);
    }

    public Mono<ServerResponse> create(ServerRequest request) {

        Mono<UpdateCreateExchangeTypeRequest> exchangeType = request.bodyToMono(UpdateCreateExchangeTypeRequest.class)
                .doOnNext(objectValidator::validate);

        return exchangeType.flatMap(exType -> ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(exchangeTypeService.createExchangeType(exType), MessageResponse.class));
    }

    public Mono<ServerResponse> update(ServerRequest request) {

        Long exchangeTypeId = Long.valueOf(request.pathVariable("id"));
        Mono<UpdateCreateExchangeTypeRequest> exchangeType = request.bodyToMono(UpdateCreateExchangeTypeRequest.class)
                .doOnNext(objectValidator::validate);

        return exchangeType.flatMap(exType -> ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(exchangeTypeService.updateExchangeType(exType, exchangeTypeId), MessageResponse.class));
    }

}

