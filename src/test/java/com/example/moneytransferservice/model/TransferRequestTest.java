package com.example.moneytransferservice.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferRequestTest {
    private static long suiteStartTime;
    private long testStartTime;
    private static TransferRequest transferRequest;

    @BeforeAll
    public static void initSuite() {
        System.out.println("Running MessageSenderTest");
        suiteStartTime = System.nanoTime();
        TransferAmount amount = new TransferAmount(1000, "rub");
        transferRequest = new TransferRequest("1234123412341234", "1234", "123", "2345234523452345", amount);
    }

    @AfterAll
    public static void completeSuite() {
        System.out.println("MessageSenderTest complete: " + (System.nanoTime() - suiteStartTime));
    }

    @BeforeEach
    public void initTest() {
        System.out.println("Starting new test");
        testStartTime = System.nanoTime();
    }

    @AfterEach
    public void finalizeTest() {
        System.out.println("Test complete:" + (System.nanoTime() - testStartTime));
    }

    @Test
    public void getterMethods_test_success() {
        String cardFromNumber = "1234123412341234";
        String cardFromValidTill = "1234";
        String cardFromCVV = "123";
        String cardToNumber = "2345234523452345";
        TransferAmount amount = new TransferAmount(1000, "rub");

        assertEquals(cardFromNumber, transferRequest.getCardFromNumber());
        assertEquals(cardFromValidTill, transferRequest.getCardFromValidTill());
        assertEquals(cardFromCVV, transferRequest.getCardFromCVV());
        assertEquals(cardToNumber, transferRequest.getCardToNumber());
        assertEquals(amount.getValue(), transferRequest.getAmount().getValue());
        assertEquals(amount.getCurrency(), transferRequest.getAmount().getCurrency());
    }
}
