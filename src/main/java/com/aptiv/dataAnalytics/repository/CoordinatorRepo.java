package com.aptiv.dataAnalytics.repository;

import com.aptiv.dataAnalytics.domain.Coordinator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordinatorRepo extends JpaRepository<Coordinator, Long> {
    Coordinator findByName(String name);
}
