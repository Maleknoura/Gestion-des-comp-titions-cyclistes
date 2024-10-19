package org.wora.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wora.Entity.Cyclist;
import org.wora.Entity.Stage;
import org.wora.Entity.StageResult;
import org.wora.Entity.embeddebals.StageResultId;
import org.wora.repository.CyclistRepository;
import org.wora.repository.StageRepository;
import org.wora.repository.StageResultRepository;
import org.wora.service.Api.StageResultService;

import java.time.Duration;

@Service
public class StageResultServiceImpl implements StageResultService {

    @Autowired
    private CyclistRepository cyclistRepository;
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

}
