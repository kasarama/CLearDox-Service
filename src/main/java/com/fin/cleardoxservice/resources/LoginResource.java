package com.fin.cleardoxservice.resources;

import com.fin.cleardoxservice.model.TokenModel;
import com.fin.cleardoxservice.model.UserDetailsRequestModel;
import com.fin.cleardoxservice.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/login")
public class LoginResource {

    @Autowired
    private RestTemplate restTemplate;


    @Value("${cleardox.login}")
    private  String loginURL;

    TokenUtils tokenUtils = new TokenUtils();
    @PostMapping("")
    public TokenModel logInToCleardox(@RequestBody UserDetailsRequestModel requestUserDetails) {
       return tokenUtils.getTokenFromClearDox(loginURL, restTemplate, requestUserDetails);
    }

}
