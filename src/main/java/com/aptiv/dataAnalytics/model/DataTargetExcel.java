package com.aptiv.dataAnalytics.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DataTargetExcel {
    private double outputTarget;
    private double prodTarget;
    private double payedTarget;
    private double hcTarget;
    private double absTarget;
    private double dtTarget;
    private double scrap;
    private double scrapTarget;
}
