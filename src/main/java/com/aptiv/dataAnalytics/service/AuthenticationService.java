package com.aptiv.dataAnalytics.service;

import com.aptiv.dataAnalytics.domain.Admin;
import com.aptiv.dataAnalytics.model.*;

public interface AuthenticationService {

    Admin signUp(SignupRequest signupRequest);

    Admin changePassword(Changepwd changepwd);

    JwtAuthenticationResponse signIn(SigninRequest signinRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
