package com.example.moneytransferservice.model;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class TransferRequest {

    @NotNull
    @Size(min = 16, max = 16, message = "First card number should be contain 16 symbols")
    @Pattern(regexp = "[0-9]+", message = "First card number is not correct")
    private final String cardFromNumber;

    @NotNull
    @Pattern(regexp = "(0[1-9]|1[0-2])/[0-9][0-9]", message = "Data is not correct")
    private final String cardFromValidTill;

    @NotNull
    @Size(min = 3, max = 3, message = "CVV code should be contain 3 symbols")
    @Pattern(regexp = "[0-9]+", message = "CVV code is not correct")
    private final String cardFromCVV;

    @NotNull
    @Size(min = 16, max = 16, message = "Second card number should be contain 16 symbols")
    @Pattern(regexp = "[0-9]+", message = "Second card number is not correct")
    private final String cardToNumber;

    @NotNull
    @Valid
    private final TransferAmount amount;

    public TransferRequest(String cardFromNumber, String cardFromValidTill, String cardFromCVV, String cardToNumber, TransferAmount amount) {
        this.cardFromNumber = cardFromNumber;
        this.cardFromValidTill = cardFromValidTill;
        this.cardFromCVV = cardFromCVV;
        this.cardToNumber = cardToNumber;
        this.amount = amount;
    }
}
