package com.example.moneytransferservice.repository;

import com.example.moneytransferservice.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Repository
public class TransferRepository {
    private final ConcurrentHashMap<String, TransferObject> transfers = new ConcurrentHashMap<>();
    public static final float COMMISSION = 0.01F;


    public String putTransferObject(TransferObject transferObject) {
        transfers.putIfAbsent(String.valueOf(transferObject.getId()), transferObject);
        log.info(transferObject.toString());
        return transferObject.getId();
    }

    public TransferObject getTransferObject(String id) {
        return transfers.get(id);
    }

    public boolean containsTransferObject(String id) {
        return transfers.containsKey(id);
    }

    public boolean smsVerify(Confirmation confirmation) {
        if (confirmation.getCode().equals("0000")) {
            return transfers.containsKey(confirmation.getOperationId());
        }
        return false;
    }
}
