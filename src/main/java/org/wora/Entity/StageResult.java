package org.wora.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.wora.Entity.embeddebals.StageResultId;

import java.time.Duration;

@Entity
@Getter
@Setter
public class StageResult {
    @EmbeddedId
    private StageResultId id;

    @ManyToOne
    @MapsId("cyclistId")
    @JoinColumn(name = "cyclist_id")
    private Cyclist cyclist;

    @ManyToOne
    @MapsId("stageId")
    @JoinColumn(name = "stage_id")
    private Stage stage;

    private Duration time;
    private Integer rank;
}
