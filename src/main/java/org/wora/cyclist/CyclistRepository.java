package org.wora.cyclist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.wora.competition.Competition;

import java.util.List;
import java.util.Optional;

public interface CyclistRepository extends JpaRepository<Cyclist,Long> {
    @Query("SELECT gr.cyclist FROM GeneralResult gr WHERE gr.competition = :competition")
    List<Cyclist> findCyclistsByCompetition(@Param("competition") Competition competition);

}
