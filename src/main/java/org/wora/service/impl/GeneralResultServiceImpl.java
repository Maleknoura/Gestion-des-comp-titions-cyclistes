package org.wora.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wora.Entity.Competition;
import org.wora.Entity.Cyclist;
import org.wora.Entity.embeddebals.GeneralResult;
import org.wora.Entity.embeddebals.GeneralResultId;
import org.wora.repository.CompetitionRepository;
import org.wora.repository.CyclistRepository;
import org.wora.repository.GeneralResultRepository;
import org.wora.service.CompetitionService;
import org.wora.service.GeneralResultService;

import java.util.Optional;

@Service
public class GeneralResultServiceImpl implements GeneralResultService {
    @Autowired
    private GeneralResultRepository generalResultRepository;
    @Autowired
    private CyclistRepository cyclistRepository;
    @Autowired
    private CompetitionService competitionService;
    @Autowired
    private CompetitionRepository competitionRepository;

    @Transactional
    @Override
    public GeneralResult save(long cyclistId, long competitionId) {
        Optional<Cyclist> cyclist=cyclistRepository.findById(cyclistId);
        Optional<Competition> competition=competitionRepository.findById(competitionId);
        if(cyclist.isPresent()&&competition.isPresent()){
            GeneralResultId generalResultId= new GeneralResultId(cyclistId,competitionId);
            GeneralResult generalResult = new GeneralResult();
            generalResult.setId(generalResultId);
            generalResult.setCyclist(cyclist.get());
            generalResult.setCompetition(competition.get());
            return generalResultRepository.save(generalResult);

        }else {
            throw new RuntimeException("Invalid cyclist or competition ID");
        }
    }

    @Override
    public void removeCyclistFromCompetition(long cyclistId, long competitionId) {
        GeneralResultId generalResultId = new GeneralResultId(cyclistId,competitionId);
        Optional<GeneralResult>generalResult=generalResultRepository.findById(generalResultId);
        if(generalResult.isPresent()){
            generalResultRepository.delete(generalResult.get());
        }
        else {
            throw new RuntimeException("Registration not found for this cyclist and competition");
        }
    }

}
