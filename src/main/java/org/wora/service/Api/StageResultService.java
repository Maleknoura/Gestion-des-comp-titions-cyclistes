package org.wora.service.Api;

import org.wora.Entity.Cyclist;
import org.wora.Entity.StageResult;

import java.time.Duration;

public interface StageResultService {
    StageResult save(long cyclistId, long stageId, Duration time, Integer rank);
}
