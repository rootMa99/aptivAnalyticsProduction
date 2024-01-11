package com.aptiv.dataAnalytics.service;

import com.aptiv.dataAnalytics.domain.Admin;
import com.aptiv.dataAnalytics.model.JwtAuthenticationResponse;
import com.aptiv.dataAnalytics.model.RefreshTokenRequest;
import com.aptiv.dataAnalytics.model.SigninRequest;
import com.aptiv.dataAnalytics.model.SignupRequest;

public interface AuthenticationService {

    Admin signUp(SignupRequest signupRequest);

    JwtAuthenticationResponse signIn(SigninRequest signinRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
