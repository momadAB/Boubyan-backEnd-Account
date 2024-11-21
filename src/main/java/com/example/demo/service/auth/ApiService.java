package com.example.demo.service.auth;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {

    private final RestTemplate restTemplate;

    private final String AUTHENTICATION_URL = "http://localhost:8080/api/v1/user/sayHi";

    public ApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public int getStatusCode(String token) {
        try {
            // Create headers and add the Bearer token
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);

            // Create an HttpEntity with the headers
            HttpEntity<Void> entity = new HttpEntity<>(headers);

            // Make the API call with headers
            ResponseEntity<String> response = restTemplate.exchange(
                    AUTHENTICATION_URL,
                    HttpMethod.GET,
                    entity,
                    String.class
            );

            return response.getStatusCodeValue(); // Returns the HTTP status code
        } catch (HttpClientErrorException e) {
            return e.getStatusCode().value(); // Returns the error status code (e.g., 404, 401)
        } catch (Exception e) {
            // Handle unexpected exceptions
            throw new RuntimeException("Failed to call API", e);
        }
    }
}
