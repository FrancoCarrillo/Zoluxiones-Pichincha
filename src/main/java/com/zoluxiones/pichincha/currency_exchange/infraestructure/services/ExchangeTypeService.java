package com.zoluxiones.pichincha.currency_exchange.infraestructure.services;

import com.zoluxiones.pichincha.currency_exchange.api.model.requests.UpdateCreateExchangeTypeRequest;
import com.zoluxiones.pichincha.currency_exchange.core.entities.Currency;
import com.zoluxiones.pichincha.currency_exchange.core.entities.ExchangeType;
import com.zoluxiones.pichincha.currency_exchange.core.repositories.CurrencyRepository;
import com.zoluxiones.pichincha.currency_exchange.core.repositories.ExchangeTypeRepository;
import com.zoluxiones.pichincha.currency_exchange.infraestructure.interfaces.IExchangeTypeService;
import com.zoluxiones.pichincha.security.api.model.responses.MessageResponse;
import com.zoluxiones.pichincha.shared.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExchangeTypeService implements IExchangeTypeService {

    private final ExchangeTypeRepository exchangeTypeRepository;
    private final CurrencyRepository currencyRepository;

    @Override
    public ExchangeType getExchangeTypeById(Long id) {
        return exchangeTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Exchange type not found"));
    }

    @Override
    public MessageResponse createExchangeType(UpdateCreateExchangeTypeRequest request) {

        Currency originCurrency = currencyRepository.findById(request.getOriginCurrencyId())
                .orElseThrow(() -> new NotFoundException("Origin currency not found"));

        Currency destinationCurrency = currencyRepository.findById(request.getDestinationCurrencyId())
                .orElseThrow(() -> new NotFoundException("Origin currency not found"));

        ExchangeType exchangeType = ExchangeType.builder()
                .exchangeRate(request.getExchangeRate())
                .originCurrency(originCurrency)
                .destinationCurrency(destinationCurrency)
                .build();

        exchangeTypeRepository.save(exchangeType);

        return MessageResponse.builder()
                .message("Exchange type created successfully")
                .build();
    }

    @Override
    public MessageResponse updateExchangeType(UpdateCreateExchangeTypeRequest request, Long exchangeTypeId) {

        ExchangeType exchangeType = exchangeTypeRepository.findById(exchangeTypeId)
                .orElseThrow(() -> new NotFoundException("Exchange type not found"));

        Currency originCurrency = currencyRepository.findById(request.getOriginCurrencyId())
                .orElseThrow(() -> new NotFoundException("Origin currency not found"));

        Currency destinationCurrency = currencyRepository.findById(request.getDestinationCurrencyId())
                .orElseThrow(() -> new NotFoundException("Origin currency not found"));

        exchangeType.setDestinationCurrency(destinationCurrency);
        exchangeType.setOriginCurrency(originCurrency);
        exchangeType.setExchangeRate(request.getExchangeRate());

        exchangeTypeRepository.save(exchangeType);

        return MessageResponse.builder()
                .message("Exchange type updated successfully")
                .build();
    }
}
