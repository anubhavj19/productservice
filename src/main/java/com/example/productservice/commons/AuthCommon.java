package com.example.productservice.commons;

import com.example.productservice.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthCommon { // Util / Common / Helper
    private RestTemplate restTemplate;

    public AuthCommon(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean validateToken(String tokenValue) {
        ResponseEntity<UserDto> response = restTemplate.getForEntity(
                "http://localhost:7070/users/validate/" + tokenValue,
                UserDto.class
        );

        UserDto userDto = response.getBody();

        return userDto != null;
    }
}
