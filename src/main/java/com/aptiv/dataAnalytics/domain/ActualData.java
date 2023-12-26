package com.aptiv.dataAnalytics.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "actual_data")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ActualData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "output")
    private Integer output;
    @Column(name = "prodh")
    private Double prodH;
    @Column(name="paidh")
    private Double paidH;
    @Column(name = "totalhc")
    private Double totalhc;
    @Column(name = "hc")
    private Double hc;
    @Column(name = "ot")
    private Double ot;
    @Column(name = "ab")
    private Double ab;
    @Column(name = "tlo")
    private Double tlo;
    @Column(name = "dt")
    private Double dt;
    @Column(name = "wsd")
    private Double wsd;
    @OneToOne(mappedBy = "actualData")
    private Data data;
}
