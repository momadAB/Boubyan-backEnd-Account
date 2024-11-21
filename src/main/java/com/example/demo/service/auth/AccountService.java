package com.example.demo.service.auth;

import com.example.demo.bo.AccountBalanceResponse;
import com.example.demo.bo.AccountTransferRequest;
import com.example.demo.entity.AccountEntity;
import com.example.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private  AccountRepository accountRepository;

    @Autowired
    private ApiService apiService;



    public void transfer(AccountTransferRequest transferRequest) {
        // Validate session
        validateSession(transferRequest.getSessionId());

        // Check sender and recipient accounts
        AccountEntity sender = accountRepository.findBySessionId(transferRequest.getSessionId());
        AccountEntity recipient = accountRepository.findByAccountNumber(transferRequest.getToAccountNumber());

        if (sender.getBalance().compareTo(transferRequest.getAmount()) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        // Perform transfer
        sender.setBalance(sender.getBalance() - transferRequest.getAmount());
        recipient.setBalance(recipient.getBalance()+ transferRequest.getAmount());

        // Save changes
        accountRepository.save(sender);
        accountRepository.save(recipient);
    }

    public AccountBalanceResponse getBalance(String sessionId, String bearerToken) {
        try {
            validateSession(bearerToken);
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private void validateSession(String bearerToken) {
        // Internal service call to validate session
        int statusCode = apiService.getStatusCode(bearerToken);
        if(statusCode != 200) {
            throw new RuntimeException("Invalid token");
        }
    }
}
