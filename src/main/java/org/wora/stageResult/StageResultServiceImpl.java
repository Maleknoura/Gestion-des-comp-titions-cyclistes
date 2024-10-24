package org.wora.stageResult;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wora.common.GenericService;
import org.wora.cyclist.Cyclist;
import org.wora.cyclist.CyclistRepository;
import org.wora.stage.Stage;
import org.wora.stage.StageRepository;
import org.wora.stageResult.dto.StageResultRequestDto;
import org.wora.stageResult.dto.StageResultResponseDto;
import org.wora.stageResult.embeddabls.StageResultId;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StageResultServiceImpl implements GenericService<StageResultRequestDto, StageResultResponseDto, StageResultId> {

    @Autowired
    private CyclistRepository cyclistRepository;

    @Autowired
    private StageRepository stageRepository;

    @Autowired
    private StageResultRepository stageResultRepository;

    @Override
    @Transactional
    public StageResultResponseDto save(StageResultRequestDto stageResultRequestDto) {
        Cyclist cyclist = cyclistRepository.findById(stageResultRequestDto.cyclistId())
                .orElseThrow(() -> new RuntimeException("Cyclist not found"));

        Stage stage = stageRepository.findById(stageResultRequestDto.stageId())
                .orElseThrow(() -> new RuntimeException("Stage not found"));

        StageResult stageResult = StageResultMapper.INSTANCE.toEntity(stageResultRequestDto);
        stageResult.setCyclist(cyclist);
        stageResult.setStage(stage);

        StageResultId id = new StageResultId(stageResultRequestDto.cyclistId(), stageResultRequestDto.stageId());
        stageResult.setId(id);

        StageResult savedStageResult = stageResultRepository.save(stageResult);
        return StageResultMapper.INSTANCE.toDto(savedStageResult);
    }

    @Override
    @Transactional
    public List<StageResultResponseDto> findAll() {
        return stageResultRepository.findAll().stream()
                .map(StageResultMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<StageResultResponseDto> findById(StageResultId id) {
        return stageResultRepository.findById(id)
                .map(StageResultMapper.INSTANCE::toDto);
    }

    @Override
    @Transactional
    public StageResultResponseDto update(StageResultRequestDto stageResultRequestDto, StageResultId id) {
        return stageResultRepository.findById(id)
                .map(existingStageResult -> {
                    StageResultMapper.INSTANCE.updateEntityFromDto(stageResultRequestDto, existingStageResult);
                    Cyclist cyclist = cyclistRepository.findById(stageResultRequestDto.cyclistId())
                            .orElseThrow(() -> new RuntimeException("Cyclist not found"));
                    Stage stage = stageRepository.findById(stageResultRequestDto.stageId())
                            .orElseThrow(() -> new RuntimeException("Stage not found"));

                    existingStageResult.setCyclist(cyclist);
                    existingStageResult.setStage(stage);

                    StageResult updatedStageResult = stageResultRepository.save(existingStageResult);
                    return StageResultMapper.INSTANCE.toDto(updatedStageResult);
                })
                .orElseThrow(() -> new RuntimeException("StageResult not found with id: " + id));
    }

    @Override
    @Transactional
    public void deleteById(StageResultId id) {
        stageResultRepository.deleteById(id);
    }
}
