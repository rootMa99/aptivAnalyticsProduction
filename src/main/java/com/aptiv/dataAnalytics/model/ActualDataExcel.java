package com.aptiv.dataAnalytics.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ActualDataExcel {
    private int output;
    private double prodH;
    private double paidH;
    private double totalhc;
    private double hc;
    private double ot;
    private double ab;
    private double tlo;
    private double dt;

}
