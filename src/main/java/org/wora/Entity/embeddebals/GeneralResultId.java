package org.wora.Entity.embeddebals;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@Embeddable
public class GeneralResultId implements Serializable {
    private Long cyclistId;
    private Long competitionId;
}
