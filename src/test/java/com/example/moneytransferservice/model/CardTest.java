package com.example.moneytransferservice.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTest {

    private static long suiteStartTime;
    private long testStartTime;
    private static Card card;

    @BeforeAll
    public static void initSuite() {
        System.out.println("Running MessageSenderTest");
        suiteStartTime = System.nanoTime();
        TransferAmount amount = new TransferAmount(1000, "rub");
        card = new Card("1234123412341234", "12/34", "123", 2000, "RUB");
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
        String number = "1234123412341234";
        String validTill = "12/34";
        String CVV = "123";
        int value = 2000;
        String currency = "RUB";

        assertEquals(number, card.getNumber());
        assertEquals(validTill, card.getValidTill());
        assertEquals(CVV, card.getCVV());
        assertEquals(value, card.getValue());
        assertEquals(currency, card.getCurrency());
    }
}
