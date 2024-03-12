package com.zoluxiones.pichincha.currency_exchange.core.entities;

import com.zoluxiones.pichincha.security.core.entities.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "currency_exchange")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@With
public class CurrencyExchange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double originAmount;
    private Double destinationAmount;

    @ManyToOne
    private User user;

    @ManyToOne
    private ExchangeType exchangeType;

    private LocalDateTime createdDate;
}
