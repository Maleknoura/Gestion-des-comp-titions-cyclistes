package org.wora.service.Api;

import org.wora.Entity.Competition;
import org.wora.Entity.GeneralResult;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CompetitionService {
    Competition save(Competition competition);
    List<Competition> findAll();
    Optional<Competition> findById(Long id);
    Competition update(Competition competition);
    List<Competition> findByDateRange(LocalDate startDate, LocalDate endDate);
    List<Competition> findByLocation(String location);
    List<GeneralResult> findCyclistRankings(Long competitionId);

}

