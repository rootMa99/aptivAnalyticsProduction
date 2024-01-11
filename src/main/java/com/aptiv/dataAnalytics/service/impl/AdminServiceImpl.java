package com.aptiv.dataAnalytics.service.impl;

import com.aptiv.dataAnalytics.repository.AdminRepo;
import com.aptiv.dataAnalytics.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepo adminRepo;
    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return adminRepo.findByAdminName(username)
                        .orElseThrow(()->new UsernameNotFoundException("admin not found"));
            }
        };
    }
}
