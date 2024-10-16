package org.wora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wora.Entity.Stage;

public interface StageRepository extends JpaRepository<Stage,Long> {
}
