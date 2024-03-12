package com.zoluxiones.pichincha.currency_exchange.core.repositories;

import com.zoluxiones.pichincha.currency_exchange.core.entities.ExchangeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeTypeRepository extends JpaRepository<ExchangeType, Long> {
}
