package com.aptiv.dataAnalytics.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "month")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Month {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "month_name")
    private String monthName;
    @OneToMany(mappedBy = "month", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Data> data;
    @OneToMany(mappedBy = "month", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Week> weeks;
}
