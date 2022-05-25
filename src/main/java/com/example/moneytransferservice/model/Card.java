package com.example.moneytransferservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
public class Card {
    private final String number;
    private final String validTill;
    private final String CVV;
    private int value;
    private final String currency;

}
