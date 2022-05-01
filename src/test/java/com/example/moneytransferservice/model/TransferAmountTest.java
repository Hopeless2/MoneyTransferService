package com.example.moneytransferservice.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferAmountTest {
    private static long suiteStartTime;
    private long testStartTime;
    final static TransferAmount transferAmount = new TransferAmount(1, "rub");

    @BeforeAll
    public static void initSuite() {
        System.out.println("Running MessageSenderTest");
        suiteStartTime = System.nanoTime();
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
    public void getValue_test_success() {
        int expected = 1;

        int result = transferAmount.getValue();

        assertEquals(expected, result);
    }

    @Test
    public void getCurrency_test_success() {
        String expected = "rub";

        String result = transferAmount.getCurrency();

        assertEquals(expected, result);
    }
}
