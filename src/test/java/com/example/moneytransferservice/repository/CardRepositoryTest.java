package com.example.moneytransferservice.repository;

import com.example.moneytransferservice.model.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardRepositoryTest {

    private static long suiteStartTime;
    private long testStartTime;
    private static CardRepository cardRepository;
    private static Card cardFrom;
    private static Card cardTo;

    @BeforeAll
    public static void initSuite() {
        System.out.println("Running MessageSenderTest");
        suiteStartTime = System.nanoTime();
        cardFrom = new Card("1234123412341234", "12/34", "123", 2000, "RUB");
        cardTo = new Card("1234123412341233", "12/34", "123", 2000, "RUB");
    }

    @AfterAll
    public static void completeSuite() {
        System.out.println("MessageSenderTest complete: " + (System.nanoTime() - suiteStartTime));
    }

    @BeforeEach
    public void initTest() {
        System.out.println("Starting new test");
        testStartTime = System.nanoTime();
        cardRepository = new CardRepository();
    }

    @AfterEach
    public void finalizeTest() {
        System.out.println("Test complete:" + (System.nanoTime() - testStartTime));
    }

    @Test
    public void getCard_test_success() {
        Card result = cardRepository.getCard("1234123412341234");

        assertEquals(cardFrom, result);
    }

    @Test
    public void containsTransferObject_test_success() {
        boolean result = cardRepository.containsCard("1234123412341234");

        assertTrue(result);
    }

    @Test
    public void compareCurrency_test_success() {
        boolean result = cardRepository.compareCurrency(cardFrom.getNumber(), cardTo.getNumber());

        assertTrue(result);
    }

    @Test
    public void enoughMoneyForTransfer_test_success() {
        TransferAmount amount = new TransferAmount(1234, "RUB");
        boolean result = cardRepository.enoughMoneyForTransfer(cardFrom.getNumber(), amount);

        assertTrue(result);
    }
}
