package org.wora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wora.Entity.Competition;

public interface CompetitionRepository extends JpaRepository<Competition,Long> {
    Competition save(Competition competition);

}
