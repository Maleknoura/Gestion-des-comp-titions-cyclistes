package org.wora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wora.Entity.Competition;
import org.wora.Entity.GeneralResult;
import org.wora.Entity.embeddebals.GeneralResultId;

import java.util.List;

public interface GeneralResultRepository extends JpaRepository<GeneralResult, GeneralResultId> {
    List<GeneralResult> findByCompetition(Competition competition);

}
