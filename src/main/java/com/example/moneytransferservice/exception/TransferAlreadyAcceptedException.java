package com.example.moneytransferservice.exception;

public class TransferAlreadyAcceptedException extends RuntimeException {
    public TransferAlreadyAcceptedException(String msg) {
        super(msg);
    }
}
