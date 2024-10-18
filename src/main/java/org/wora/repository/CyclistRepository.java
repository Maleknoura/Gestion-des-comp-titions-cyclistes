package org.wora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.wora.Entity.Competition;
import org.wora.Entity.Cyclist;

import java.util.List;

public interface CyclistRepository extends JpaRepository<Cyclist,Long> {
    @Query("SELECT gr.cyclist FROM GeneralResult gr WHERE gr.competition = :competition")
    List<Cyclist> findCyclistsByCompetition(@Param("competition") Competition competition);
}
