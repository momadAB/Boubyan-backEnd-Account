package com.example.demo.controller;

import com.example.demo.bo.AccountBalanceResponse;
import com.example.demo.bo.AccountTransferRequest;
import com.example.demo.bo.TransferResponse;
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

    private static final String STOCK_API ="http://localhost:8080/api/v1/user/transfer";

    private final AccountService accountService;

    public AccountController(RestTemplate restTemplate, AccountService accountService) {
        this.restTemplate = restTemplate;
        this.accountService = accountService;
    }


    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody AccountTransferRequest transferRequest) {

        HttpHeaders headers = new HttpHeaders();

        // Extract the Bearer token
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String bearerToken = authorizationHeader.substring(7); // Remove "Bearer " prefix
            headers.set("Authorization", "Bearer " + bearerToken); // Set the Authorization header
        }

        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create the request entity with headers and body
        HttpEntity<AccountTransferRequest> request = new HttpEntity<>(transferRequest, headers);

        // Make the REST call and map the response to TransferResponse
        ResponseEntity<TransferResponse> response = restTemplate.postForEntity(
                STOCK_API,
                request,
                TransferResponse.class
        );

        // Return the response from the external API to the client
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }



    @GetMapping("/get-balance")
    public ResponseEntity<AccountBalanceResponse> getBalance( @RequestHeader("Authorization") String authorizationHeader) {
        String bearerToken = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            bearerToken = authorizationHeader.substring(7); // Remove "Bearer " prefix
        }
        AccountBalanceResponse response = accountService.getBalance(bearerToken);
        return ResponseEntity.ok(response);
    }




}
