package org.wora.stage;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wora.common.GenericService;
import org.wora.competition.Competition;
import org.wora.competition.CompetitionRepository;
import org.wora.stage.dto.StageRequestDto;
import org.wora.stage.dto.StageResponseDto;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class StageServiceImpl implements GenericService<StageRequestDto, StageResponseDto, Long> {

    @Autowired
    private StageRepository stageRepository;

    @Autowired
    private CompetitionRepository competitionRepository;

    @Override
    @Transactional
    public StageResponseDto save(StageRequestDto stageRequestDto) {
        Competition competition = competitionRepository.findByName(stageRequestDto.competitionName()).orElse(null);

        if (competition == null) {
            return new StageResponseDto("Competition not found", 0, null, null);
        }

        Stage stage = StageMapper.INSTANCE.toEntity(stageRequestDto);

        stage.setCompetition(competition);

        Stage savedStage = stageRepository.save(stage);

        return StageMapper.INSTANCE.toDto(savedStage);
    }


    @Override
    @Transactional
    public List<StageResponseDto> findAll() {
        return stageRepository.findAll().stream()
                .map(StageMapper.INSTANCE::toDto)
                .toList();
    }

    @Override
    @Transactional
    public StageResponseDto update(StageRequestDto stageRequestDto, Long id) {
        Optional<Stage> optionalStage = stageRepository.findById(id);

        if (optionalStage.isEmpty()) {
            return new StageResponseDto("Stage not found", 0, null, null);
        }

        Stage existingStage = optionalStage.get();
        StageMapper.INSTANCE.updateEntityFromDto(stageRequestDto, existingStage);

        Stage updatedStage = stageRepository.save(existingStage);

        return StageMapper.INSTANCE.toDto(updatedStage);
    }

    @Override
    @Transactional
    public Optional<StageResponseDto> findById(Long id) {
        return stageRepository.findById(id)
                .map(StageMapper.INSTANCE::toDto);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        stageRepository.deleteById(id);
    }
}
