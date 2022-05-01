package com.example.moneytransferservice.controller;


import com.example.moneytransferservice.exception.TransferAlreadyAcceptedException;
import com.example.moneytransferservice.exception.TransferConfirmationException;
import com.example.moneytransferservice.exception.TransferInitiationException;
import com.example.moneytransferservice.model.*;
import com.example.moneytransferservice.service.TransferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;

@Validated
@RestController
@Slf4j
@RequestMapping("/")
public class TransferController {
    private final TransferService service;

    public TransferController(TransferService service) {
        this.service = service;
    }

    @PostMapping("/transfer")
    public Response transfer(@RequestBody @Valid TransferRequest transfer) {
        return service.transfer(transfer);
    }

    @PostMapping("/confirmOperation")
    public Response confirmOperation(@RequestBody @Valid Confirmation confirmation) {
        return service.confirmOperation(confirmation);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    String handleValidationException(ValidationException e) {
        log.error(e.getMessage());
        return "Incorrect format of the entered card data, please try again";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    String handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage());
        return "Incorrect format of the entered card data, please try again";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TransferAlreadyAcceptedException.class)
    String handleTransferAlreadyAcceptedException(TransferAlreadyAcceptedException e) {
        log.error(e.getMessage());
        return e.getMessage();
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(TransferInitiationException.class)
    String handleTransferInitiationException(TransferInitiationException e) {
        log.error(e.getMessage());
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(TransferConfirmationException.class)
    String handleTransferConfirmationException(TransferConfirmationException e) {
        log.error(e.getMessage());
        return e.getMessage();
    }

}
