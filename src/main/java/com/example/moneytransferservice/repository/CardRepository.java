package com.example.moneytransferservice.repository;

import com.example.moneytransferservice.model.Card;
import com.example.moneytransferservice.model.TransferAmount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Repository
public class CardRepository {

    private final ConcurrentHashMap<String, Card> cards = new ConcurrentHashMap<>();

    public CardRepository() {
        cards.put("1234123412341234", new Card("1234123412341234", "12/34", "123", 2000, "RUB"));
        cards.put("1234123412341233", new Card("1234123412341233", "12/34", "123", 2000, "RUB"));
    }

    public Card getCard(String cardNumber) {
        return cards.get(cardNumber);
    }

    public boolean containsCard(String cardNumber) {
        return cards.containsKey(cardNumber);
    }

    public boolean compareCurrency(String cardFromNumber, String cardToNumber) {
        return cards.get(cardFromNumber).getCurrency().equals(cards.get(cardToNumber).getCurrency());
    }

    public boolean enoughMoneyForTransfer(String cardFromNumber, TransferAmount amount) {
        return cards.get(cardFromNumber).getValue() >= amount.getValue();
    }

    public void setCardValue(String cardNumber, int newValue) {
        Card card = cards.get(cardNumber);
        card.setValue(newValue);
        cards.putIfAbsent(card.getNumber(), card);
    }
}
