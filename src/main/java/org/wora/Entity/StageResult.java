package org.wora.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.wora.Entity.embeddebals.StageResultId;

import java.time.Duration;

@Entity
@Getter
@Setter
@NoArgsConstructor
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
    public StageResult(StageResultId id,Cyclist cyclist,Stage stage,Duration time,Integer rank){
        this.id=id;
        this.cyclist=cyclist;
        this.stage=stage;
        this.time=time;
        this.rank=rank;

    }

}
