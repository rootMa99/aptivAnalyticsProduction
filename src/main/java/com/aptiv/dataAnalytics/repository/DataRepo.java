package com.aptiv.dataAnalytics.repository;


import com.aptiv.dataAnalytics.domain.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepo extends JpaRepository<Data, Long> {
}
