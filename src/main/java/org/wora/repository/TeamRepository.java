package org.wora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wora.Entity.Team;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team,Long> {

}
