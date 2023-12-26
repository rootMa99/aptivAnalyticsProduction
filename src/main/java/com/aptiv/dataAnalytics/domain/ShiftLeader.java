package com.aptiv.dataAnalytics.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "shift_leader")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ShiftLeader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "shiftLeader", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Data> data;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coordinator_id")
    private Coordinator coordinator;
    @OneToMany(mappedBy = "shiftLeader", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TeamLeader> teamLeaders;
    @OneToOne(mappedBy = "shiftLeader")
    private FileEntity file;

}
