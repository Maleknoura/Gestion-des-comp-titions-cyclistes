package org.wora.service.Api;

import org.wora.Entity.Competition;
import org.wora.Entity.GeneralResult;

import java.util.List;

public interface GeneralResultService {
    GeneralResult save(long cyclistId, long competitionId);
    void removeCyclistFromCompetition(long cyclistId, long competitionId);
    List<GeneralResult> findByCompetition(Competition competition);



}
