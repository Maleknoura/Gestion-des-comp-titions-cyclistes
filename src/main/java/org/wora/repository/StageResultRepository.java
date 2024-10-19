package org.wora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wora.Entity.StageResult;
import org.wora.Entity.embeddebals.StageResultId;

import java.util.List;

public interface StageResultRepository extends JpaRepository<StageResult, StageResultId> {
    List<StageResult> findByCyclistIdAndStageCompetitionId(Long cyclistId, Long competitionId);

}
