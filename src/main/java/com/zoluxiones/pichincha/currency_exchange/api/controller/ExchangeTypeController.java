package com.zoluxiones.pichincha.currency_exchange.api.controller;

import com.zoluxiones.pichincha.currency_exchange.api.model.requests.UpdateCreateExchangeTypeRequest;
import com.zoluxiones.pichincha.currency_exchange.core.entities.ExchangeType;
import com.zoluxiones.pichincha.currency_exchange.infraestructure.interfaces.IExchangeTypeService;
import com.zoluxiones.pichincha.security.api.model.responses.MessageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/exchange-type")
@AllArgsConstructor
@Tag(name = "Exchange Type")
public class ExchangeTypeController {

    private final IExchangeTypeService exchangeTypeService;


    @Operation(summary = "Get exchange type")
    @GetMapping("/{id}")
    public ResponseEntity<ExchangeType> getById(@PathVariable Long id) {
        return ResponseEntity.ok(exchangeTypeService.getExchangeTypeById(id));
    }

    @Operation(summary = "Register exchange type")
    @PostMapping
    public ResponseEntity<MessageResponse> register(@RequestBody UpdateCreateExchangeTypeRequest request) {
        return ResponseEntity.ok(exchangeTypeService.createExchangeType(request));
    }

    @Operation(summary = "Update exchange type")
    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> update(@RequestBody UpdateCreateExchangeTypeRequest request, @PathVariable Long id) {
        return ResponseEntity.ok(exchangeTypeService.updateExchangeType(request, id));
    }

}
