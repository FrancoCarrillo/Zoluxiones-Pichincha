package com.zoluxiones.pichincha.currency_exchange.core.repositories;

import com.zoluxiones.pichincha.currency_exchange.core.entities.ExchangeType;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeTypeRepository extends ReactiveCrudRepository<ExchangeType, Long> {
}
