package com.example.demo.service.auth;

import com.example.demo.bo.AccountBalanceResponse;
import com.example.demo.bo.AccountTransferRequest;
//import com.example.demo.entity.AccountEntity;
//import com.example.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private ApiService apiService;

    public AccountBalanceResponse getBalance(String bearerToken) {
        try {
            validateSession(bearerToken);
            return apiService.getBalance(bearerToken);
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
