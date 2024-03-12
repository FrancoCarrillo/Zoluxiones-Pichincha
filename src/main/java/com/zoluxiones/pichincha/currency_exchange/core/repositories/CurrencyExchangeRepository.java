package com.zoluxiones.pichincha.currency_exchange.core.repositories;

import com.zoluxiones.pichincha.currency_exchange.core.entities.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, String> {
}
