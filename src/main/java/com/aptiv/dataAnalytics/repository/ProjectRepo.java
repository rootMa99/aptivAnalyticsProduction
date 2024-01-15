package com.aptiv.dataAnalytics.repository;

import com.aptiv.dataAnalytics.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Long> {
    Project findByName(String name);

    List<Project> findByDataDatecrBetween(Date startDate, Date endDate);
}
