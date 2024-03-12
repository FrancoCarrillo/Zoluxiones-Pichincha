package com.zoluxiones.pichincha.currency_exchange.api.model.responses;

import com.zoluxiones.pichincha.security.core.entities.User;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CurrencyExchangeResponse {
    private Double originAmount;
    private Double destinationAmount;
    private LocalDateTime createdDate;
    private User user;


}
