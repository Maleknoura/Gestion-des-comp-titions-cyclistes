package org.wora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wora.Entity.Competition;
import org.wora.Entity.Stage;

import java.util.List;

public interface StageRepository extends JpaRepository<Stage,Long> {

}
