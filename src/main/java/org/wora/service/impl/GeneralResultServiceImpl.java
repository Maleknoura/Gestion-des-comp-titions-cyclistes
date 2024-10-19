package org.wora.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wora.Entity.*;
import org.wora.Entity.embeddebals.GeneralResultId;
import org.wora.Entity.embeddebals.StageResultId;
import org.wora.repository.*;
import org.wora.service.Api.CompetitionService;
import org.wora.service.Api.GeneralResultService;

import java.time.Duration;
import java.util.List;
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
    @Autowired
    private StageRepository stageRepository;
    @Autowired
    private StageResultRepository stageResultRepository;

    @Transactional
    @Override
    public StageResult save(long cyclistId, long stageId, Duration time, Integer rank) {
        Cyclist cyclist = cyclistRepository.findById(cyclistId)
                .orElseThrow(() -> new RuntimeException("Cyclist not found"));
        Stage stage = stageRepository.findById(stageId)
                .orElseThrow(() -> new RuntimeException("Stage not found"));

        StageResultId id = new StageResultId(cyclistId, stageId);
        StageResult stageResult = new StageResult(id, cyclist, stage, time, rank);

        return stageResultRepository.save(stageResult);
    }

    @Override
    public void removeCyclistFromCompetition(long cyclistId, long competitionId) {
        GeneralResultId generalResultId = new GeneralResultId(cyclistId, competitionId);
        Optional<GeneralResult> generalResult = generalResultRepository.findById(generalResultId);
        if (generalResult.isPresent()) {
            generalResultRepository.delete(generalResult.get());
        } else {
            throw new RuntimeException("Registration not found for this cyclist and competition");
        }
    }




    @Override

    public List<GeneralResult> findByCompetitionIdOrderByGeneralRankAsc(Long competitionId) {
        return generalResultRepository.findByCompetitionIdOrderByGeneralRankAsc(competitionId);
    }



}

