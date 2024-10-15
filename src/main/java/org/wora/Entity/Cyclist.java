package org.wora.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.wora.Entity.embeddebals.GeneralResult;
import org.wora.Entity.embeddebals.StageResult;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@Entity
public class Cyclist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String nationality;
    private String team;

    @OneToMany(mappedBy = "cyclist", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StageResult> stageResults = new HashSet<>();

    @OneToMany(mappedBy = "cyclist", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GeneralResult> generalResults = new HashSet<>();
}

