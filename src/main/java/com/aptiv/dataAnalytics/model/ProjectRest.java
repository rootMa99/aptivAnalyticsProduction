package com.aptiv.dataAnalytics.model;

import com.aptiv.dataAnalytics.domain.Data;
import com.aptiv.dataAnalytics.domain.Family;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProjectRest {

    private String name;

    private List<DataExcel> data;


}
