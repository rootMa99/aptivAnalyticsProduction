package com.aptiv.dataAnalytics.model;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private String token;
    private String refreshToken;
    private String role;
    private String userName;
}
