package com.aptiv.dataAnalytics.repository;

import com.aptiv.dataAnalytics.domain.ActualData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActualDataRepo extends JpaRepository<ActualData, Long> {
}
