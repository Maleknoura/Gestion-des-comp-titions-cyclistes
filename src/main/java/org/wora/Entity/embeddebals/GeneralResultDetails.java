package org.wora.Entity.embeddebals;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
@Getter
@Setter
@Embeddable
public class GeneralResultDetails {
    private LocalTime totalTime;
    private int finalRank;
}
