package com.aptiv.dataAnalytics.service;

import com.aptiv.dataAnalytics.model.CoordinatorRest;

import java.util.List;

public interface CoordinatorService {
    CoordinatorRest getCoordinatorData(String name);

    List<CoordinatorRest> getAllCoordinatorData();
}
