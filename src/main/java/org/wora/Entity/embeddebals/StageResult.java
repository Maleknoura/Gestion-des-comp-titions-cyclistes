package org.wora.Entity.embeddebals;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.wora.Entity.Cyclist;
import org.wora.Entity.Stage;

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

    @Embedded
    private StageResultDetails details;
}
