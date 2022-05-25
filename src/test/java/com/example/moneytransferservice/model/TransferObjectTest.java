package com.example.moneytransferservice.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferObjectTest {
    private static long suiteStartTime;
    private long testStartTime;
    private static TransferObject transferObject;

    @BeforeAll
    public static void initSuite() {
        System.out.println("Running MessageSenderTest");
        suiteStartTime = System.nanoTime();
        TransferAmount amount = new TransferAmount(1000, "rub");
        TransferRequest transferRequest = new TransferRequest("1234123412341234", "1234", "123", "2345234523452345", amount);
        transferObject = new TransferObject(transferRequest, "1", TransactionStatus.AWAIT_CONFIRMATION);
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
        String id = "1";
        String cardFromNumber = "1234123412341234";
        String cardFromValidTill = "1234";
        String cardFromCVV = "123";
        String cardToNumber = "2345234523452345";
        TransferAmount amount = new TransferAmount(1000, "rub");
        double commission = 10;
        TransactionStatus transactionStatus = TransactionStatus.AWAIT_CONFIRMATION;

        assertEquals(id, transferObject.getId());
        assertEquals(cardFromNumber, transferObject.getCardFromNumber());
        assertEquals(cardFromValidTill, transferObject.getCardFromValidTill());
        assertEquals(cardFromCVV, transferObject.getCardFromCVV());
        assertEquals(cardToNumber, transferObject.getCardToNumber());
        assertEquals(amount.getValue(), transferObject.getAmount().getValue());
        assertEquals(amount.getCurrency(), transferObject.getAmount().getCurrency());
        assertEquals(commission, transferObject.getCommission());
        assertEquals(transactionStatus, transferObject.getTransactionStatus());
    }
}
