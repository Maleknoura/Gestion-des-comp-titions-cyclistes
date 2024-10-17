package org.wora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wora.Entity.embeddebals.GeneralResult;
import org.wora.Entity.embeddebals.GeneralResultId;
import org.wora.Entity.embeddebals.StageResultId;
public interface GeneralResultRepository extends JpaRepository<GeneralResult, GeneralResultId> {
}
