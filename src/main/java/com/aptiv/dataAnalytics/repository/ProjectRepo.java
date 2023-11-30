package com.aptiv.dataAnalytics.repository;

import com.aptiv.dataAnalytics.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Long> {
}
