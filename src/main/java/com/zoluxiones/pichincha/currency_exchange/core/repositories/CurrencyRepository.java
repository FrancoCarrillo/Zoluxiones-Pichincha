package com.zoluxiones.pichincha.currency_exchange.core.repositories;

import com.zoluxiones.pichincha.currency_exchange.core.entities.CurrencyExchange;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends ReactiveCrudRepository<CurrencyExchange, Long> {
}
