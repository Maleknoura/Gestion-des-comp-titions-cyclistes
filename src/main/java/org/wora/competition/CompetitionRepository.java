package org.wora.competition;

import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CompetitionRepository extends JpaRepository<Competition,Long> {
    List<Competition> findByStartDateBetween(LocalDate startDate, LocalDate endDate);
    List<Competition> findByLocation(String location);
    Optional<Competition> findByName(String name);

}
