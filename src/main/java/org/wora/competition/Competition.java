package org.wora.competition;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.wora.generalResult.GeneralResult;
import org.wora.stage.Stage;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Competition implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String location;

    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Stage> stages = new HashSet<>();

    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GeneralResult> generalResults = new HashSet<>();
    public Competition(){}
    public Competition(String name,LocalDate startDate,LocalDate endDate,String location){
    this.name=name;
    this.startDate=startDate;
    this.endDate=endDate;
    this.location=location;
    }
}