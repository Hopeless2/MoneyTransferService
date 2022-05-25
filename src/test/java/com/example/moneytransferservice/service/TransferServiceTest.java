package com.example.moneytransferservice.service;

import com.example.moneytransferservice.model.*;
import com.example.moneytransferservice.repository.CardRepository;
import com.example.moneytransferservice.repository.TransferRepository;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferServiceTest {
    private static long suiteStartTime;
    private long testStartTime;
    private static TransferRequest transferRequest;
    private static TransferService service;


    @BeforeAll
    public static void initSuite() {
        System.out.println("Running MessageSenderTest");
        suiteStartTime = System.nanoTime();
        TransferAmount amount = new TransferAmount(1234, "RUB");
        transferRequest = new TransferRequest("1234123412341234", "12/34", "123", "1234123412341233", amount);
    }

    @AfterAll
    public static void completeSuite() {
        System.out.println("MessageSenderTest complete: " + (System.nanoTime() - suiteStartTime));
    }

    @BeforeEach
    public void initTest() {
        System.out.println("Starting new test");
        testStartTime = System.nanoTime();
        TransferRepository transferRepository = new TransferRepository();
        CardRepository cardRepository = new CardRepository();
        service = new TransferService(transferRepository, cardRepository);
    }

    @AfterEach
    public void finalizeTest() {
        System.out.println("Test complete:" + (System.nanoTime() - testStartTime));
    }

    @Test
    public void transfer_test_success() {
        Response expected = new Response("1");

        Response result = service.transfer(transferRequest);

        assertEquals(expected.getOperationId(), result.getOperationId());
    }

    @Test
    public void confirmOperation_test_success() {
        Response expected = new Response("1");
        Confirmation confirmation = new Confirmation("1", "0000");

        service.transfer(transferRequest);
        Response result = service.confirmOperation(confirmation);

        assertEquals(expected.getOperationId(), result.getOperationId());
    }

}
