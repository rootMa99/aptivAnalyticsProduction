package com.aptiv.dataAnalytics.controllers;

import com.aptiv.dataAnalytics.domain.Admin;
import com.aptiv.dataAnalytics.model.JwtAuthenticationResponse;
import com.aptiv.dataAnalytics.model.RefreshTokenRequest;
import com.aptiv.dataAnalytics.model.SigninRequest;
import com.aptiv.dataAnalytics.model.SignupRequest;
import com.aptiv.dataAnalytics.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;


    @PostMapping("/signUp")
    public ResponseEntity<Admin> signUp(@RequestBody SignupRequest signupRequest){
        return ResponseEntity.ok(authenticationService.signUp(signupRequest));
    }
    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest signupRequest){
        return ResponseEntity.ok(authenticationService.signIn(signupRequest));
    }
    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> signinref(@RequestBody RefreshTokenRequest signupRequest){
        return ResponseEntity.ok(authenticationService.refreshToken(signupRequest));
    }
}
