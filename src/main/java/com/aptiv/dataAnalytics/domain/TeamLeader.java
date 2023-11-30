package com.aptiv.dataAnalytics.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "team_leader")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeamLeader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "teamLeader", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Data> data;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shift_leader_id")
    private ShiftLeader shiftLeader;
    @OneToMany(mappedBy = "teamLeader", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Crew> crews;
}
