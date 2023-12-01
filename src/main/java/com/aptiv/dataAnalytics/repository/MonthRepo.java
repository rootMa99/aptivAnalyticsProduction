package com.aptiv.dataAnalytics.repository;

import com.aptiv.dataAnalytics.domain.Month;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthRepo extends JpaRepository<Month, Long> {
    Month findByMonthName(String monthName);
}
