package com.zoluxiones.pichincha.exchange.api.routers;

import com.zoluxiones.pichincha.exchange.api.handlers.ExchangeTypeHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@Slf4j
public class ExchangeTypeRouter {

    private static final String PATH_EXCHANGE_TYPE = "/api/exchange-type";

    @Bean
    RouterFunction<ServerResponse> exchangeTypeRtr(ExchangeTypeHandler handler) {
        return RouterFunctions.route()
                .GET(PATH_EXCHANGE_TYPE + "/{id}", handler::findById)
                .POST(PATH_EXCHANGE_TYPE, handler::create)
                .PUT(PATH_EXCHANGE_TYPE + "/{id}", handler::update)
                .build();
    }
}
