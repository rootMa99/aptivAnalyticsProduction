package com.aptiv.dataAnalytics.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "week")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Week {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "week_name")
    private String weekName;
    @OneToMany(mappedBy = "week", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Data> data;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "month_id")
    private Month month;
}
