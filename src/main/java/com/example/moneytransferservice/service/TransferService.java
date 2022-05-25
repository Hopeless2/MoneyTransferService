package com.example.moneytransferservice.service;

import com.example.moneytransferservice.exception.TransferAlreadyAcceptedException;
import com.example.moneytransferservice.exception.TransferConfirmationException;
import com.example.moneytransferservice.exception.TransferInitiationException;
import com.example.moneytransferservice.model.*;
import com.example.moneytransferservice.repository.CardRepository;
import com.example.moneytransferservice.repository.TransferRepository;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class TransferService {
    private final TransferRepository transferRepository;
    private final CardRepository cardRepository;
    private final AtomicLong newId = new AtomicLong(1);

    public TransferService(TransferRepository repository, CardRepository cardRepository) {
        this.transferRepository = repository;
        this.cardRepository = cardRepository;
    }

    public Response transfer(TransferRequest transfer) {
        if (!cardRepository.containsCard(transfer.getCardFromNumber())) {
            TransferObject transferObject = new TransferObject(transfer, String.valueOf(newId.getAndIncrement()), TransactionStatus.REJECTED);
            transferRepository.putTransferObject(transferObject);
            throw new TransferInitiationException("The card from which the transfer attempt is made does not exist");
        }
        if (!cardRepository.containsCard(transfer.getCardToNumber())) {
            TransferObject transferObject = new TransferObject(transfer, String.valueOf(newId.getAndIncrement()), TransactionStatus.REJECTED);
            transferRepository.putTransferObject(transferObject);
            throw new TransferInitiationException("The card to which the transfer is being attempted does not exist");
        }
        if (!cardRepository.compareCurrency(transfer.getCardFromNumber(), transfer.getCardToNumber())) {
            TransferObject transferObject = new TransferObject(transfer, String.valueOf(newId.getAndIncrement()), TransactionStatus.REJECTED);
            transferRepository.putTransferObject(transferObject);
            throw new TransferInitiationException("The card accounts have different currencies");
        }
        if (transfer.getCardFromNumber().equals(transfer.getCardToNumber())) {
            TransferObject transferObject = new TransferObject(transfer, String.valueOf(newId.getAndIncrement()), TransactionStatus.REJECTED);
            transferRepository.putTransferObject(transferObject);
            throw new TransferInitiationException("Transfer from the card to itself is not possible");
        }
        if (cardRepository.getCard(transfer.getCardFromNumber()).getValue() < transfer.getAmount().getValue()) {
            TransferObject transferObject = new TransferObject(transfer, String.valueOf(newId.getAndIncrement()), TransactionStatus.REJECTED);
            transferRepository.putTransferObject(transferObject);
            throw new TransferInitiationException("Insufficient funds to make a money transfer");
        }
        TransferObject transferObject = new TransferObject(transfer, String.valueOf(newId.getAndIncrement()), TransactionStatus.AWAIT_CONFIRMATION);
        String id = transferRepository.putTransferObject(transferObject);

        return new Response(id);
    }

    public Response confirmOperation(Confirmation confirmation) {
        if (!transferRepository.containsTransferObject(confirmation.getOperationId())) {
            throw new TransferConfirmationException("The translation was not found in the database");
        }

        if (!transferRepository.smsVerify(confirmation)) {
            throw new TransferConfirmationException("Incorrect SMS confirmation");
        }

        TransferObject transferObject = transferRepository.getTransferObject(confirmation.getOperationId());

        if (transferObject.getTransactionStatus().equals(TransactionStatus.REJECTED)) {
            throw new TransferAlreadyAcceptedException("The transfer of funds has already been rejected");
        }

        if (transferObject.getTransactionStatus().equals(TransactionStatus.COMPLETED)) {
            throw new TransferAlreadyAcceptedException("The transfer of funds has already been confirmed");
        }

        if (cardRepository.getCard(transferObject.getCardFromNumber()).getValue() < transferObject.getAmount().getValue()) {
            transferObject.setTransactionStatus(TransactionStatus.REJECTED);
            transferRepository.putTransferObject(transferObject);
            throw new TransferInitiationException("Insufficient funds to make a money transfer");
        }

        transferObject.setTransactionStatus(TransactionStatus.COMPLETED);
        Card cardFrom = cardRepository.getCard(transferObject.getCardFromNumber());
        Card cardTo = cardRepository.getCard(transferObject.getCardToNumber());

        int valueFrom = cardFrom.getValue() - transferObject.getAmount().getValue();
        int valueTo = (int) (cardTo.getValue() + transferObject.getAmount().getValue() * (1 + TransferRepository.COMMISSION));

        cardRepository.setCardValue(cardFrom.getNumber(), valueFrom);
        cardRepository.setCardValue(cardTo.getNumber(), valueTo);

        String id = transferRepository.putTransferObject(transferObject);

        return new Response(id);
    }


}
