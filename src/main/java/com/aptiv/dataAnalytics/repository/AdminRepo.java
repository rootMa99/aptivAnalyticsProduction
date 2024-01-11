package com.aptiv.dataAnalytics.repository;

import com.aptiv.dataAnalytics.domain.Admin;
import com.aptiv.dataAnalytics.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {

    Optional<Admin> findByAdminName(String name);
    Admin findByRole(Role role);
}
