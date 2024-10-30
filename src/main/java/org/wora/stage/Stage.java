package org.wora.stage;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.wora.stageResult.StageResult;
import org.wora.competition.Competition;

import java.time.LocalDate;
import java.util.*;
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double distance;
    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "competition_id")
    private Competition competition;

    @OneToMany(mappedBy = "stage", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StageResult> results = new HashSet<>();

    public Stage(String firstStage, double v, LocalDate of) {
        this.name=name;
        this.distance=distance;
        this.date=date;
    }
}
