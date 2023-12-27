package com.aptiv.dataAnalytics.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DataExcel {
    private String project;
    private String projectUriPic;
    private String family;
    private String crew;
    private String teamLeader;
    private String shiftLeader;
    private String shiftLeaderUriPic;
    private String coordinator;
    private String coordinatorUriPic;
    private String month;
    private String week;
    private Date date;
    private ActualDataExcel actualDataExcel;
    private DataTargetExcel dataTargetExcel;

}
