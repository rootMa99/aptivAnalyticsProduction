package com.aptiv.dataAnalytics.repository;

import com.aptiv.dataAnalytics.domain.ShiftLeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiftLeaderRepo extends JpaRepository<ShiftLeader, Long> {
}
