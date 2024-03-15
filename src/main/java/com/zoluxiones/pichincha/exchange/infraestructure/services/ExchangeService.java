package com.zoluxiones.pichincha.exchange.infraestructure.services;

import com.zoluxiones.pichincha.exchange.api.model.requests.CreateExchangeRequest;
import com.zoluxiones.pichincha.exchange.core.entities.Exchange;
import com.zoluxiones.pichincha.exchange.core.repositories.ExchangeRepository;
import com.zoluxiones.pichincha.exchange.core.repositories.CurrencyRepository;
import com.zoluxiones.pichincha.exchange.core.repositories.ExchangeTypeRepository;
import com.zoluxiones.pichincha.exchange.infraestructure.interfaces.IExchangeService;
import com.zoluxiones.pichincha.shared.exceptions.CustomException;
import com.zoluxiones.pichincha.shared.models.response.MessageResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ExchangeService implements IExchangeService {

    private final CurrencyRepository currencyRepository;
    //    private final UserRepository userRepository;
    private final ExchangeRepository exchangeRepository;
    private final ExchangeTypeRepository exchangeTypeRepository;


    // TODO: Validate if user exists
    @Override
    public Mono<MessageResponse> createExchange(CreateExchangeRequest request) {
        return currencyRepository.findById(request.getOriginCurrencyId())
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, "Origin currency not found")))
                .flatMap(originCurrency -> currencyRepository.findById(request.getDestinationCurrencyId())
                        .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, "Destination currency not found")))
                        .flatMap(destinationCurrency -> exchangeTypeRepository.findByOriginCurrencyIdAndDestinationCurrencyId(originCurrency.getId(), destinationCurrency.getId())
                                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, "Exchange type not found")))
                                .flatMap(exchangeType -> {
                                    Exchange exchange = Exchange.builder()
                                            .originAmount(request.getOriginAmount())
                                            .destinationAmount(request.getOriginAmount() * exchangeType.getExchangeRate())
                                            .userId(request.getUserId())
                                            .exchangeTypeId(exchangeType.getId())
                                            .createdDate(LocalDateTime.now())
                                            .build();

                                    return exchangeRepository.save(exchange)
                                            .thenReturn(MessageResponse.builder()
                                                    .message("Exchange created successfully")
                                                    .build());
                                })));
    }
}
