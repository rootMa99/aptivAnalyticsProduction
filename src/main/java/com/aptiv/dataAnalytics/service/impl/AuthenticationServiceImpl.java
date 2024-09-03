package com.aptiv.dataAnalytics.service.impl;

import com.aptiv.dataAnalytics.domain.Admin;
import com.aptiv.dataAnalytics.domain.Role;
import com.aptiv.dataAnalytics.model.*;
import com.aptiv.dataAnalytics.repository.AdminRepo;
import com.aptiv.dataAnalytics.service.AuthenticationService;
import com.aptiv.dataAnalytics.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AdminRepo userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Override
    public Admin signUp(SignupRequest signupRequest) {
        Admin admin=new Admin();
        admin.setAdminName(signupRequest.getAdminName());
        admin.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        admin.setRole(Role.ADMIN);

        return userRepository.save(admin);
    }
    @Override
    public Admin changePassword(Changepwd changepwd){
        Admin admin=userRepository.findByRole(Role.ADMIN);
        admin.setPassword(new BCryptPasswordEncoder().encode(changepwd.getPassword()));
        return userRepository.save(admin);
    }
    @Override
    public JwtAuthenticationResponse signIn(SigninRequest signinRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getAdminName(),
                signinRequest.getPassword()));
        var admin= userRepository.findByAdminName(signinRequest.getAdminName())
                .orElseThrow(()->new UsernameNotFoundException("invalid email or password"));
        var jwt=jwtService.generateToken(admin);
        var refreshToken=jwtService.generateRefreshToken(new HashMap<>(), admin);

        JwtAuthenticationResponse jwtAuthenticationResponse= new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        jwtAuthenticationResponse.setRole(admin.getRole().name());
        jwtAuthenticationResponse.setUserName(admin.getAdminName());
        return jwtAuthenticationResponse;
    }

    @Override
    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String adminName= jwtService.extractUserName(refreshTokenRequest.getToken());
        Admin admin=userRepository.findByAdminName(adminName).orElseThrow();
        if(jwtService.isTokenValid(refreshTokenRequest.getToken(), admin)){
            var jwt =jwtService.generateToken(admin);
            JwtAuthenticationResponse jwtAuthenticationResponse= new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthenticationResponse;
        }
        return null;
    }
}
