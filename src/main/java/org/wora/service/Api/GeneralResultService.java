package org.wora.service.Api;

import org.wora.Entity.Competition;
import org.wora.Entity.GeneralResult;
import org.wora.Entity.StageResult;

import java.time.Duration;
import java.util.List;

public interface GeneralResultService {
    StageResult save(long cyclistId, long stageId, Duration time, Integer rank);
    void removeCyclistFromCompetition(long cyclistId, long competitionId);
    List<GeneralResult> findByCompetitionIdOrderByGeneralRankAsc(Long competitionId);



}
