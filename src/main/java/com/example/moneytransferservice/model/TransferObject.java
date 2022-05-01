package com.example.moneytransferservice.model;

import com.example.moneytransferservice.repository.TransferRepository;
import lombok.Data;

@Data
public class TransferObject {
    private final String id;
    private final String cardFromNumber;
    private final String cardFromValidTill;
    private final String cardFromCVV;
    private final String cardToNumber;
    private final TransferAmount amount;
    private final double commission;
    private boolean accepted = false;

    public TransferObject(TransferRequest request, String id) {
        cardFromNumber = request.getCardFromNumber();
        cardFromValidTill = request.getCardFromValidTill();
        cardFromCVV = request.getCardFromCVV();
        cardToNumber = request.getCardToNumber();
        amount = request.getAmount();
        commission = Math.round(amount.getValue() * TransferRepository.COMMISSION);
        this.id = id;
    }

    public boolean getAccepted() {
        return accepted;
    }

    public void accept() {
        this.accepted = true;
    }

    @Override
    public String toString() {
        return "TransferObject{" +
                "id='" + id + '\'' +
                ", cardFromNumber='" + cardFromNumber + '\'' +
                ", cardFromValidTill='" + cardFromValidTill + '\'' +
                ", cardFromCVV='" + cardFromCVV + '\'' +
                ", cardToNumber='" + cardToNumber + '\'' +
                ", amount=" + amount +
                ", commission=" + commission +
                ", accepted=" + accepted +
                '}';
    }
}
