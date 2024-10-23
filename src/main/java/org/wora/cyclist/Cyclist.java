package org.wora.cyclist;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.wora.generalResult.GeneralResult;
import org.wora.stageResult.StageResult;
import org.wora.team.Team;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Cyclist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "le prenom ne doit pas etre null")
    private String firstName;
    @NotBlank(message="le nom ne doit pas etre null")
    private String lastName;
    @Past(message="le date doit etre dans le passe")
    @NotNull(message = "required Birthdate")
    private LocalDate dateOfBirth;
    @NotBlank(message="la nationnalite ne doit pas etre null")
    private String nationality;


    @OneToMany(mappedBy = "cyclist", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StageResult> stageResults = new HashSet<>();

    @OneToMany(mappedBy = "cyclist", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GeneralResult> generalResults = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public Cyclist(String firstName, String lastName, LocalDate dateOfBirth, String nationality, Team team) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.dateOfBirth = dateOfBirth;
        this.nationality=nationality;
        this.team=team;
    }

}

