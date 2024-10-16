package org.wora.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wora.Entity.Competition;
import org.wora.repository.CompetitionRepository;
import org.wora.service.CompetitionService;

@Service
public class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionRepository competitionRepository;
    public CompetitionServiceImpl(CompetitionRepository competitionRepository){
        this.competitionRepository=competitionRepository;
    }

    @Autowired
    public Competition save(Competition competition){
        return null;
    }
}
