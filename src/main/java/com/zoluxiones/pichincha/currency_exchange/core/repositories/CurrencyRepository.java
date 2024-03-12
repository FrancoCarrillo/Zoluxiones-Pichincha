package com.zoluxiones.pichincha.currency_exchange.core.repositories;

import com.zoluxiones.pichincha.currency_exchange.core.entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    boolean existsByName(String name);
}
