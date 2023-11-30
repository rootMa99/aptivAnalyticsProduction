package com.aptiv.dataAnalytics.repository;

import com.aptiv.dataAnalytics.domain.Crew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrewRepo extends JpaRepository<Crew, Long> {
}
