package com.aptiv.dataAnalytics.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name ="coordinator")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Coordinator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "coordinator", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Data> data;
    @OneToOne(mappedBy = "coordinator")
    private FileEntity file;
}
