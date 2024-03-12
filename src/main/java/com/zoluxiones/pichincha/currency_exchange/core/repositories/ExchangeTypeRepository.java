package com.zoluxiones.pichincha.currency_exchange.core.repositories;

import com.zoluxiones.pichincha.currency_exchange.core.entities.Currency;
import com.zoluxiones.pichincha.currency_exchange.core.entities.ExchangeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExchangeTypeRepository extends JpaRepository<ExchangeType, Long> {

    Optional<ExchangeType> findByOriginCurrencyAndDestinationCurrency(Currency originCurrency, Currency destinationCurrency);
}
