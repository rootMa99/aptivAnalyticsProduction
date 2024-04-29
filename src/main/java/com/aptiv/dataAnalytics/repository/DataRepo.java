package com.aptiv.dataAnalytics.repository;


import com.aptiv.dataAnalytics.domain.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DataRepo extends JpaRepository<Data, Long> {
    List<Data> findAllByProjectName(String name);
    Data findByDatecrAndCrewName(Date datec, String crew);
}
