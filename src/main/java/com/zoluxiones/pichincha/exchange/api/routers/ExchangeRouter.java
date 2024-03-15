package com.zoluxiones.pichincha.exchange.api.routers;


import com.zoluxiones.pichincha.exchange.api.handlers.ExchangeHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@Slf4j
public class ExchangeRouter {

    private static final String PATH_EXCHANGE = "/api/exchange";

    @Bean
    RouterFunction<ServerResponse> exchangeRtr(ExchangeHandler handler) {
        return RouterFunctions.route()
                .POST(PATH_EXCHANGE, handler::create)
                .build();
    }

}
