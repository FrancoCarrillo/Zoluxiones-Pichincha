package com.zoluxiones.pichincha.currency_exchange.infraestructure.services;

import com.zoluxiones.pichincha.currency_exchange.api.model.requests.CreateCurrencyExchangeRequest;
import com.zoluxiones.pichincha.currency_exchange.api.model.responses.CurrencyExchangeResponse;
import com.zoluxiones.pichincha.currency_exchange.api.model.responses.ExchangeTypeResponse;
import com.zoluxiones.pichincha.currency_exchange.core.entities.Currency;
import com.zoluxiones.pichincha.currency_exchange.core.entities.CurrencyExchange;
import com.zoluxiones.pichincha.currency_exchange.core.entities.ExchangeType;
import com.zoluxiones.pichincha.currency_exchange.core.repositories.CurrencyExchangeRepository;
import com.zoluxiones.pichincha.currency_exchange.core.repositories.CurrencyRepository;
import com.zoluxiones.pichincha.currency_exchange.core.repositories.ExchangeTypeRepository;
import com.zoluxiones.pichincha.currency_exchange.infraestructure.interfaces.ICurrencyExchangeService;
import com.zoluxiones.pichincha.security.api.model.responses.MessageResponse;
import com.zoluxiones.pichincha.security.core.entities.User;
import com.zoluxiones.pichincha.security.core.repositories.UserRepository;
import com.zoluxiones.pichincha.shared.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CurrencyExchangeService implements ICurrencyExchangeService {

    private final CurrencyRepository currencyRepository;
    private final UserRepository userRepository;
    private final ExchangeTypeRepository exchangeTypeRepository;
    private final CurrencyExchangeRepository currencyExchangeRepository;

    @Override
    public MessageResponse createCurrencyExchange(CreateCurrencyExchangeRequest request) {

        Currency originCurrency = currencyRepository.findById(request.getOriginCurrencyId())
                .orElseThrow(() -> new NotFoundException("Origin currency not found"));

        Currency destinationCurrency = currencyRepository.findById(request.getDestinationCurrencyId())
                .orElseThrow(() -> new NotFoundException("Destination currency not found"));

        ExchangeType exchangeType = exchangeTypeRepository.findByOriginCurrencyAndDestinationCurrency(originCurrency, destinationCurrency)
                .orElseThrow(() -> new NotFoundException("Exchange type not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found"));

        CurrencyExchange currencyExchange = CurrencyExchange.builder()
                .originAmount(request.getOriginAmount())
                .destinationAmount(request.getOriginAmount() * exchangeType.getExchangeRate())
                .user(user)
                .exchangeType(exchangeType)
                .createdDate(LocalDateTime.now())
                .build();

        currencyExchangeRepository.save(currencyExchange);

        return MessageResponse.builder()
                .message("Currency exchange created successfully")
                .build();
    }
}
