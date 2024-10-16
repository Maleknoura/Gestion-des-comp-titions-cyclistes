package org.wora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wora.Entity.Competition;

import java.time.LocalDate;
import java.util.List;

public interface CompetitionRepository extends JpaRepository<Competition,Long> {
    List<Competition> findByStartDateBetween(LocalDate startDate, LocalDate endDate);
    List<Competition> findByLocation(String location);
}
