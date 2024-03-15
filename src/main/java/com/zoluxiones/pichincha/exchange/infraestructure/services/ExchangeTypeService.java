package com.zoluxiones.pichincha.exchange.infraestructure.services;

import com.zoluxiones.pichincha.exchange.api.model.requests.UpdateCreateExchangeTypeRequest;
import com.zoluxiones.pichincha.exchange.core.entities.Currency;
import com.zoluxiones.pichincha.exchange.core.entities.ExchangeType;
import com.zoluxiones.pichincha.exchange.core.repositories.CurrencyRepository;
import com.zoluxiones.pichincha.exchange.core.repositories.ExchangeTypeRepository;
import com.zoluxiones.pichincha.exchange.infraestructure.interfaces.IExchangeTypeService;
import com.zoluxiones.pichincha.shared.exceptions.CustomException;
import com.zoluxiones.pichincha.shared.models.response.MessageResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExchangeTypeService implements IExchangeTypeService {
    private final ExchangeTypeRepository exchangeTypeRepository;
    private final CurrencyRepository currencyRepository;

    @Override
    public Mono<ExchangeType> getExchangeTypeById(Long id) {
        return exchangeTypeRepository.findById(id)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, "Exchange type not found")));
    }

    @Override
    public Mono<MessageResponse> createExchangeType(UpdateCreateExchangeTypeRequest request) {

        return currencyRepository.findById(request.getOriginCurrencyId())
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, "Origin currency not found")))
                .flatMap(originCurrency -> currencyRepository.findById(request.getDestinationCurrencyId())
                        .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND,"Destination currency not found")))
                        .flatMap(destinationCurrency -> {
                            ExchangeType exchangeType = ExchangeType.builder()
                                    .exchangeRate(request.getExchangeRate())
                                    .originCurrencyId(originCurrency.getId())
                                    .destinationCurrencyId(destinationCurrency.getId())
                                    .build();

                            return exchangeTypeRepository.save(exchangeType)
                                    .thenReturn(MessageResponse.builder()
                                            .message("Exchange type created successfully")
                                            .build());
                        }));
    }

    @Override
    public Mono<MessageResponse> updateExchangeType(UpdateCreateExchangeTypeRequest request, Long exchangeTypeId) {
        return exchangeTypeRepository.findById(exchangeTypeId)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND,"Exchange type not found")))
                .flatMap(exchangeType -> currencyRepository.findById(request.getOriginCurrencyId())
                        .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND,"Origin currency not found")))
                        .flatMap(originCurrency -> currencyRepository.findById(request.getDestinationCurrencyId())
                                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND,"Destination currency not found")))
                                .flatMap(destinationCurrency -> {
                                    exchangeType.setExchangeRate(request.getExchangeRate());
                                    exchangeType.setOriginCurrencyId(originCurrency.getId());
                                    exchangeType.setDestinationCurrencyId(destinationCurrency.getId());

                                    return exchangeTypeRepository.save(exchangeType)
                                            .thenReturn(MessageResponse.builder()
                                                    .message("Exchange type updated successfully")
                                                    .build());
                                })));
    }
}
