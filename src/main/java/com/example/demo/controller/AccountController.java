package com.example.demo.controller;

import com.example.demo.bo.AccountBalanceResponse;
import com.example.demo.bo.AccountTransferRequest;
import com.example.demo.service.auth.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/api")

public class AccountController {
    @Autowired
    @Lazy
    private final RestTemplate restTemplate;
    private static final String STOCK_API ="http://localhost:8080/api";
private final AccountService accountService;

    public AccountController(RestTemplate restTemplate, AccountService accountService) {
        this.restTemplate = restTemplate;
        this.accountService = accountService;
    }


    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody  AccountTransferRequest transferRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AccountTransferRequest> request = new HttpEntity<>(transferRequest, headers);

        accountService.transfer(transferRequest);
        return restTemplate.postForEntity(STOCK_API, request, AccountTransferRequest.class);
    }

    @GetMapping("/get-balance")
    public ResponseEntity<AccountBalanceResponse> getBalance(@RequestHeader("session_id") String sessionId,
                                                             @RequestHeader String authorizationHeader) {
        String bearerToken = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            bearerToken = authorizationHeader.substring(7); // Remove "Bearer " prefix
        }
        AccountBalanceResponse response = accountService.getBalance(sessionId, bearerToken);
        return ResponseEntity.ok(response);
    }




}
