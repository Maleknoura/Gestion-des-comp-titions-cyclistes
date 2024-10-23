package org.wora.generalResult;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wora.generalResult.embeddabls.GeneralResultId;

import java.util.List;

public interface GeneralResultRepository extends JpaRepository<GeneralResult, GeneralResultId> {

    List<GeneralResult> findByCompetitionIdOrderByGeneralRankAsc(Long competitionId);

}
