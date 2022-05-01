package com.example.moneytransferservice;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.testcontainers.containers.GenericContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MoneyTransferServiceApplicationTests {

    @Autowired
    TestRestTemplate restTemplate;
    public static GenericContainer<?> transferApp = new GenericContainer<>("moneytransferapi:1.0")
            .withExposedPorts(5500);

    @BeforeAll
    public static void setUp() {
        transferApp.start();
    }

    @Test
    void moneyTransferServiceTest_transfer_success() {
        final String expected = "{\"operationId\":\"1\"}";
        String requestJson = "{\n" +
                "    \"cardFromNumber\": \"1234123412341234\",\n" +
                "    \"cardFromValidTill\": \"12/34\",\n" +
                "    \"cardFromCVV\": \"123\",\n" +
                "    \"cardToNumber\": \"1234123412341235\",\n" +
                "    \"amount\": {\n" +
                "        \"value\": 1234,\n" +
                "        \"currency\": \"r\"\n" +
                "    }\n" +
                "}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);

        String result = restTemplate.postForObject("/transfer", entity, String.class);

        assertEquals(expected, result);
    }

    @Test
    void contextLoads() {
    }

    @Test
    void moneyTransferServiceTest_confirmOperation_success() {
        final String expected = "{\"operationId\":\"1\"}";
        String requestJson = "{\n" +
                "    \"operationId\": \"1\",\n" +
                "    \"code\": \"0000\"\n" +
                "}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);

        String result = restTemplate.postForObject("/confirmOperation", entity, String.class);

        assertEquals(expected, result);
    }

}
