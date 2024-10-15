package org.wora.Entity.embeddebals;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.wora.Entity.Competition;
import org.wora.Entity.Cyclist;

import java.time.Duration;
@Getter
@Setter
@Entity
public class GeneralResult {
    @EmbeddedId
    private GeneralResultId id;
    @ManyToOne
    @JoinColumn(name = "cyclist_id")
    private Cyclist cyclist;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competition;

    private Duration generalTime;
    private Integer generalRank;}
