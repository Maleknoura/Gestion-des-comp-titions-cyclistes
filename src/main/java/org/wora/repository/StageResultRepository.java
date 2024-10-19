package org.wora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wora.Entity.StageResult;
import org.wora.Entity.embeddebals.StageResultId;

public interface StageResultRepository extends JpaRepository<StageResult, StageResultId> {
}
