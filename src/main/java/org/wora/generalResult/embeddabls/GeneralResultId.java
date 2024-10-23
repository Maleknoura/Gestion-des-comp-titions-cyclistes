package org.wora.generalResult.embeddabls;

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
    private Long competitionId;
    private Long cyclistId;
    public GeneralResultId(long cyclistId,long competitionId){
        this.cyclistId=cyclistId;
        this.competitionId=competitionId;
    }
}
