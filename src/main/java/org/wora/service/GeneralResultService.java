package org.wora.service;

import org.wora.Entity.embeddebals.GeneralResult;

public interface GeneralResultService {
    GeneralResult save(long cyclistId, long competitionId);
    void removeCyclistFromCompetition(long cyclistId, long competitionId);

}
