package com.zoluxiones.pichincha.currency_exchange.api.controller;

import com.zoluxiones.pichincha.currency_exchange.api.model.requests.CreateCurrencyExchangeRequest;
import com.zoluxiones.pichincha.currency_exchange.infraestructure.interfaces.ICurrencyExchangeService;
import com.zoluxiones.pichincha.security.api.model.responses.MessageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/currency-exchange")
@AllArgsConstructor
@Tag(name = "Currency Exchange")
public class CurrencyExchangeController {

    private final ICurrencyExchangeService currencyExchangeService;


    @Operation(summary = "Register currency exchange")
    @PostMapping
    public ResponseEntity<MessageResponse> register(@RequestBody CreateCurrencyExchangeRequest request) {
        return ResponseEntity.ok(currencyExchangeService.createCurrencyExchange(request));
    }

}
