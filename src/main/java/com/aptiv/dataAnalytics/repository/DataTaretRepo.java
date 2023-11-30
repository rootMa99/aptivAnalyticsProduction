package com.aptiv.dataAnalytics.repository;

import com.aptiv.dataAnalytics.domain.DataTarget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataTaretRepo extends JpaRepository<DataTarget, Long> {
}
