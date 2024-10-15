package org.wora.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.wora.Entity.embeddebals.StageResult;

import java.time.LocalDate;
import java.util.*;
@Getter
@Setter
@Entity
public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double distance;
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competition_id")
    private Competition competition;

    @OneToMany(mappedBy = "stage", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StageResult> results = new HashSet<>();
}
