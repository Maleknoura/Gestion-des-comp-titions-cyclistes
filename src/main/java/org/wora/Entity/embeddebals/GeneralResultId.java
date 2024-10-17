package org.wora.Entity.embeddebals;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class GeneralResultId implements Serializable {
    private Long cyclistId;
    private Long competitionId;
    public GeneralResultId(long cyclistId,long competitionId){
        this.cyclistId=cyclistId;
        this.competitionId=competitionId;
    }
}
