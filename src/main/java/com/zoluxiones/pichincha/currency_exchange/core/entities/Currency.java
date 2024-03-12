package com.zoluxiones.pichincha.currency_exchange.core.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "currency")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
