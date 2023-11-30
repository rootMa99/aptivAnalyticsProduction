package com.aptiv.dataAnalytics.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "data_target")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DataTarget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "output_target")
    private Double outputTarget;
    @Column(name = "prod_target")
    private Double prodTarget;
    @Column(name = "payed_target")
    private Double payedTarget;
    @Column(name = "hc_target")
    private Double hcTarget;
    @Column(name = "abs_target")
    private Double absTarget;
    @Column(name = "dt_target")
    private Double dtTarget;
    @Column(name = "scrap")
    private Double scrap;
    @Column(name = "scrap_target")
    private Double scrapTarget;
    @OneToOne(mappedBy = "dataTarget")
    private Data data;
}
