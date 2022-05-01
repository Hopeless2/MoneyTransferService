package com.example.moneytransferservice.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class TransferAmount {

    @NotNull
    @Min(1)
    private final int value;

    @NotNull(message = "Currency is null")
    private final String currency;

    public TransferAmount(int value, String currency) {
        this.value = value;
        this.currency = currency;
    }
}
