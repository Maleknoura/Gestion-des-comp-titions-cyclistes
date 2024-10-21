package org.wora.stageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wora.cyclist.Cyclist;
import org.wora.stage.Stage;
import org.wora.cyclist.CyclistRepository;
import org.wora.stage.StageRepository;
import org.wora.common.GenericService;
import org.wora.stageResult.embeddabls.StageResultId;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Service
public class StageResultServiceImpl implements GenericService<StageResult, StageResultId> {

    @Autowired
    private CyclistRepository cyclistRepository;

    @Autowired
    private StageRepository stageRepository;

    @Autowired
    private StageResultRepository stageResultRepository;

    @Transactional
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
    public StageResult save(StageResult entity) {
        return stageResultRepository.save(entity);
    }

    @Override
    public List<StageResult> findAll() {
        return stageResultRepository.findAll();
    }

    @Override
    public StageResult update(StageResult entity) {
        if (!stageResultRepository.existsById(entity.getId())) {
            throw new RuntimeException("StageResult not found");
        }
        return stageResultRepository.save(entity);
    }

    @Override
    public Optional<StageResult> findById(StageResultId id) {
        return stageResultRepository.findById(id);
    }
}
