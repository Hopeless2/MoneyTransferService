package com.example.moneytransferservice.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class Confirmation {

    @NotNull(message = "Operation ID is null")
    @Size(min = 1, message = "Operation ID is not correct")
    @Pattern(regexp = "[0-9]+", message = "Operation ID contains not only numeric characters")
    private final String operationId;

    @NotNull
    @Size(min = 4, max = 4)
    @Pattern(regexp = "[0-9]+", message = "Code is not correct")
    private final String code;

    public Confirmation(String operationId, String code) {
        this.operationId = operationId;
        this.code = code;
    }
}
