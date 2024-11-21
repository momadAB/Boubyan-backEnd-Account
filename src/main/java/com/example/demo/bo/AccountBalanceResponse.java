package com.example.demo.bo;

import java.math.BigDecimal;

public class AccountBalanceResponse {
    private String accountNumber;
    private double balance;

    public AccountBalanceResponse(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }



    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }
}
