package com.aptiv.dataAnalytics.model;

import lombok.Data;

import java.util.List;

@Data
public class CoordinatorRest {
    private String coordinatorName;
    private List<DataExcel> dataExcelList;
}
