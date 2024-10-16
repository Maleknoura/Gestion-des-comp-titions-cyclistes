package org.wora.service.impl;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.wora.Entity.Competition;
import org.wora.repository.CompetitionRepository;
import org.wora.service.CompetitionService;

@Service
public class CompetitionServiceImpl implements CompetitionService {
    @Autowired
    private CompetitionRepository competitionRepository;


    public Competition save(@Valid Competition competition){
        return competitionRepository.save(competition);
    }
}
