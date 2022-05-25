package com.example.moneytransferservice.repository;

import com.example.moneytransferservice.model.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransferRepositoryTest {
    private static long suiteStartTime;
    private long testStartTime;
    private static TransferRepository transferRepository;
    private static TransferRequest transferRequest;
    private static TransferObject transferObject;

    @BeforeAll
    public static void initSuite() {
        System.out.println("Running MessageSenderTest");
        suiteStartTime = System.nanoTime();
        TransferAmount amount = new TransferAmount(1234, "rub");
        transferRequest = new TransferRequest("1234123412341234", "1234", "123", "2345234523452345", amount);
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
        transferRepository = new TransferRepository();
    }

    @AfterEach
    public void finalizeTest() {
        System.out.println("Test complete:" + (System.nanoTime() - testStartTime));
    }

    @Test
    public void putTransferObject_test_success() {
        String expected = "1";

        String result = transferRepository.putTransferObject(transferObject);

        assertEquals(expected, result);
    }

    @Test
    public void getTransferObject_test_success() {
        transferRepository.putTransferObject(transferObject);
        TransferObject result = transferRepository.getTransferObject("1");

        assertEquals(transferObject, result);
    }

    @Test
    public void containsTransferObject_test_successful() {
        transferRepository.putTransferObject(transferObject);
        boolean result = transferRepository.containsTransferObject("1");

        assertTrue(result);
    }

    @Test
    public void smsVerify_test_success() {
        Confirmation confirmation = new Confirmation("1", "0000");

        transferRepository.putTransferObject(transferObject);
        boolean result = transferRepository.smsVerify(confirmation);

        assertTrue(result);
    }
}
