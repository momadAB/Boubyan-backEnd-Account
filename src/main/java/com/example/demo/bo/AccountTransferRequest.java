package com.example.demo.bo;

import java.math.BigDecimal;

public class AccountTransferRequest {
    private String toAccountNumber;
    private double amount;

    public AccountTransferRequest() {}

    public AccountTransferRequest(String toAccountNumber, double amount) {
        this.toAccountNumber = toAccountNumber;
        this.amount = amount;
    }

    // Getters and setters
    public String getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(String toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
