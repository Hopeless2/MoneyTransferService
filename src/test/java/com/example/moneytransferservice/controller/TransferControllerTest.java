package com.example.moneytransferservice.controller;

import com.example.moneytransferservice.model.Confirmation;
import com.example.moneytransferservice.model.Response;
import com.example.moneytransferservice.model.TransferAmount;
import com.example.moneytransferservice.model.TransferRequest;
import com.example.moneytransferservice.service.TransferService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransferControllerTest {
    private static long suiteStartTime;
    private long testStartTime;
    private static TransferController transferController;

    @Mock
    private TransferService mockedService;

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
    public void transfer_test_success() {
        TransferAmount amount = new TransferAmount(1234, "rub");
        TransferRequest transferRequest = new TransferRequest("1234123412341234", "1234", "123", "2345234523452345", amount);
        Response expected = new Response("1");
        when(mockedService.transfer(transferRequest))
                .thenReturn(expected);

        transferController = new TransferController(mockedService);
        Response result = transferController.transfer(transferRequest);

        assertEquals(expected.getOperationId(), result.getOperationId());

    }

    @Test
    public void confirmOperation_test_success() {
        Confirmation confirmation = new Confirmation("1", "0000");
        Response expected = new Response("1");
        when(mockedService.confirmOperation(confirmation))
                .thenReturn(expected);

        transferController = new TransferController(mockedService);
        Response result = transferController.confirmOperation(confirmation);

        assertEquals(expected.getOperationId(), result.getOperationId());
    }
}
