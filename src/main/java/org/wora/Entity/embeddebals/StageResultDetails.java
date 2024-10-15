package org.wora.Entity.embeddebals;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalTime;

@Getter
@Setter
@Embeddable
public class StageResultDetails {
    private LocalTime startTime;
    private Duration duration;
}
