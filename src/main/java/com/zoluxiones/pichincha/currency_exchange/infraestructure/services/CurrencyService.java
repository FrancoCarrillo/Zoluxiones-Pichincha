package com.zoluxiones.pichincha.currency_exchange.infraestructure.services;

import com.zoluxiones.pichincha.currency_exchange.core.entities.Currency;
import com.zoluxiones.pichincha.currency_exchange.core.repositories.CurrencyRepository;
import com.zoluxiones.pichincha.currency_exchange.infraestructure.interfaces.ICurrencyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class CurrencyService implements ICurrencyService {

    private CurrencyRepository currencyRepository;

    private static final String[] DEFAULT_CURRENCIES = {"USD", "EUR", "PEN"};

    @Override
    public void seedCurrency() {
        Arrays.stream(DEFAULT_CURRENCIES).forEach(name -> {
            if(!currencyRepository.existsByName(name)) {
                currencyRepository.save(new Currency().withName(name));
            }
        } );
    }
}
