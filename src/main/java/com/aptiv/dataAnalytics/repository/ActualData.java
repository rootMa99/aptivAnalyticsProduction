package com.aptiv.dataAnalytics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActualData extends JpaRepository<ActualData, Long> {
}
