package org.wora.stageResult;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wora.stageResult.embeddabls.StageResultId;

import java.util.List;

public interface StageResultRepository extends JpaRepository<StageResult, StageResultId> {
    List<StageResult> findByCyclistIdAndStageCompetitionId(Long cyclistId, Long competitionId);

}
