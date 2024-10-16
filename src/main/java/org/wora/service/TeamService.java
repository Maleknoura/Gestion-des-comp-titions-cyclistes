package org.wora.service;

import org.wora.Entity.Team;
import java.util.List;
import java.util.Optional;

public interface TeamService {
    Team save(Team team);
     List<Team>findAll();
    Team update(Team team);
    Optional<Team> findById(long id);
}
