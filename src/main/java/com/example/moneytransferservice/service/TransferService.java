package com.example.moneytransferservice.service;

import com.example.moneytransferservice.exception.TransferAlreadyAcceptedException;
import com.example.moneytransferservice.exception.TransferConfirmationException;
import com.example.moneytransferservice.exception.TransferInitiationException;
import com.example.moneytransferservice.model.Confirmation;
import com.example.moneytransferservice.model.Response;
import com.example.moneytransferservice.model.TransferObject;
import com.example.moneytransferservice.model.TransferRequest;
import com.example.moneytransferservice.repository.TransferRepository;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class TransferService {
    private final TransferRepository repository;
    private final AtomicLong newId = new AtomicLong(1);

    public TransferService(TransferRepository repository) {
        this.repository = repository;
    }

    public Response transfer(TransferRequest transfer) {
        if (!repository.checkCardsInDB(transfer)) {
            throw new TransferInitiationException("The card is not in the database");
        }

        TransferObject transferObject = new TransferObject(transfer, String.valueOf(newId.getAndIncrement()));
        String id = repository.putTransferObject(transferObject);

        return new Response(id);
    }

    public Response confirmOperation(Confirmation confirmation) {
        if (!repository.smsVerify(confirmation)) {
            throw new TransferConfirmationException("Incorrect SMS confirmation");
        }

        if (!repository.containsTransferObject(confirmation.getOperationId())) {
            throw new TransferConfirmationException("The translation was not found in the database");
        }

        TransferObject transferObject = repository.getTransferObject(confirmation.getOperationId());

        if (transferObject.getAccepted()) {
            throw new TransferAlreadyAcceptedException("Transfer already accepted");
        }
        transferObject.accept();
        String id = repository.putTransferObject(transferObject);

        return new Response(id);
    }


}
