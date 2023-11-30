package com.aptiv.dataAnalytics.repository;

import com.aptiv.dataAnalytics.domain.Week;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeekRepo extends JpaRepository<Week, Long> {
}
