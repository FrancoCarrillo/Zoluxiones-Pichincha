package com.zoluxiones.pichincha.exchange.api.handlers;

import com.zoluxiones.pichincha.exchange.api.model.requests.CreateExchangeRequest;
import com.zoluxiones.pichincha.exchange.infraestructure.interfaces.IExchangeService;
import com.zoluxiones.pichincha.shared.models.response.MessageResponse;
import com.zoluxiones.pichincha.shared.validation.ObjectValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class ExchangeHandler {

    private final IExchangeService exchangeService;
    private final ObjectValidator objectValidator;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Mono<ServerResponse> create(ServerRequest request) {
        Mono<CreateExchangeRequest> exchange = request.bodyToMono(CreateExchangeRequest.class)
                .doOnNext(objectValidator::validate);

        return exchange.flatMap(ex -> ServerResponse.ok()
                .body(exchangeService.createExchange(ex), MessageResponse.class));

    }

}
