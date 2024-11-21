package com.example.demo.bo;

import java.math.BigDecimal;

public class AccountBalanceResponse {
    private double balance;

    public AccountBalanceResponse() {}

    public AccountBalanceResponse(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }
}
