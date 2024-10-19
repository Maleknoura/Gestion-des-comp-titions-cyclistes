package org.wora.Entity.embeddebals;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class StageResultId implements Serializable {
    private Long cyclistId;
    private Long stageId;

public StageResultId(Long cyclistId,Long stageId){
 this.stageId=stageId;
 this.cyclistId=cyclistId;
}
}
