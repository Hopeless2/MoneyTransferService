package com.example.moneytransferservice.model;

import lombok.Data;

@Data
public class Response {
    private final String operationId;

    public Response(String operationId) {
        this.operationId = operationId;
    }
}
