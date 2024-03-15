package com.zoluxiones.pichincha.exchange.core.repositories;

import com.zoluxiones.pichincha.exchange.core.entities.ExchangeType;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ExchangeTypeRepository extends ReactiveCrudRepository<ExchangeType, Long> {

    Mono<ExchangeType> findByOriginCurrencyIdAndDestinationCurrencyId(Long originCurrency, Long destinationCurrency);
}
