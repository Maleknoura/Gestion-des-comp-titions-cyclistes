package org.wora.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.wora.Entity.embeddebals.GeneralResultId;

import java.time.Duration;
@Getter
@Setter
@Entity
public class GeneralResult {
    @EmbeddedId
    private GeneralResultId id;

    @ManyToOne
    @MapsId("cyclistId")
    @JoinColumn(name = "cyclist_id")
    private Cyclist cyclist;

    @ManyToOne
    @MapsId("competitionId")
    @JoinColumn(name = "competition_id")
    private Competition competition;

    private Duration generalTime;
    private Integer generalRank;
}
