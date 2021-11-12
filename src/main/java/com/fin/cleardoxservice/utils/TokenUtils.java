package com.fin.cleardoxservice.utils;

import com.fin.cleardoxservice.model.TokenModel;
import com.fin.cleardoxservice.model.UserDetailsRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

public class TokenUtils {


    public  TokenModel getTokenFromClearDox(String loginURL, RestTemplate restTemplate, UserDetailsRequestModel requestUserDetails) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserDetailsRequestModel> request =
                new HttpEntity<>(requestUserDetails, headers);
        LocalDateTime actualDateTime = LocalDateTime.now();
        LocalDateTime expiryDateTime = actualDateTime.plusHours(4);
        TokenModel tokenModel =
                restTemplate.postForObject(loginURL, request, TokenModel.class);
        tokenModel.setExpiryDateTime(expiryDateTime);
        return tokenModel;
    }
}
