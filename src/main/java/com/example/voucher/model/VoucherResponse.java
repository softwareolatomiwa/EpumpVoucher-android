package com.example.voucher.model;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Olatomiwa on 2/17/2018.
 */

public class VoucherResponse {
    public UUID Id;
    public String Pin;
    public UUID TransactionId;
    public String Recipient;
    public boolean Completed;
    public Date Date;

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public String getPin() {
        return Pin;
    }

    public void setPin(String pin) {
        Pin = pin;
    }

    public UUID getTransactionId() {
        return TransactionId;
    }

    public void setTransactionId(UUID transactionId) {
        TransactionId = transactionId;
    }

    public String getRecipient() {
        return Recipient;
    }

    public void setRecipient(String recipient) {
        Recipient = recipient;
    }

    public boolean isCompleted() {
        return Completed;
    }

    public void setCompleted(boolean completed) {
        Completed = completed;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }
}
