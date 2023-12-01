package com.aptiv.dataAnalytics.repository;

import com.aptiv.dataAnalytics.domain.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyRepo extends JpaRepository<Family, Long> {
    Family findByName(String name);
}
