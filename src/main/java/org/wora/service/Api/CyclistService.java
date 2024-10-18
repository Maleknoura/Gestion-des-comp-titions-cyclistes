package org.wora.service.Api;

import org.wora.Entity.Competition;
import org.wora.Entity.Cyclist;

import java.util.List;
import java.util.Optional;

public interface CyclistService {
    Cyclist save(Cyclist cyclist);
    List<Cyclist> findall();
    Optional<Cyclist> findbyId(long id);
    Cyclist update(Cyclist cyclist);
//    List<Cyclist>findCyclistByCompetition(Competition competition);
}
