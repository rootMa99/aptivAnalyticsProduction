package com.aptiv.dataAnalytics.model;

import lombok.Data;

@Data
public class SigninRequest {
    private String adminName;
    private String password;
}
