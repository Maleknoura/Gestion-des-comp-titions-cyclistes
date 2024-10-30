package org.wora.generalResult;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wora.competition.Competition;
import org.wora.competition.CompetitionRepository;
import org.wora.cyclist.Cyclist;
import org.wora.cyclist.CyclistRepository;
import org.wora.generalResult.dto.GeneralResultRequestDto;
import org.wora.generalResult.dto.GeneralResultResponseDto;
import org.wora.generalResult.embeddabls.GeneralResultId;
import org.wora.stage.Stage;
import org.wora.stageResult.StageResult;
import org.wora.stageResult.StageResultMapper;
import org.wora.stageResult.dto.StageResultRequestDto;
import org.wora.stageResult.dto.StageResultResponseDto;
import org.wora.stageResult.embeddabls.StageResultId;

import java.util.List;
import java.util.Optional;

@Service
public class GeneralResultServiceImpl {

    @Autowired
    private GeneralResultRepository generalResultRepository;

    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private CyclistRepository cyclistRepository;
    private static final Logger logger = LoggerFactory.getLogger(GeneralResultServiceImpl.class);


    @Transactional
    public GeneralResultResponseDto save(GeneralResultRequestDto generalResultRequestDto) {
        System.out.println("Attempting to save General Result: {}"+ generalResultRequestDto);

        if (generalResultRequestDto == null) {
            System.out.println("Request DTO cannot be null.");

            throw new IllegalArgumentException("Request DTO cannot be null.");
        }

        Competition competition = competitionRepository.findById(generalResultRequestDto.competitionId()).orElse(null);
        Cyclist cyclist = cyclistRepository.findById(generalResultRequestDto.cyclistId()).orElse(null);

        if (cyclist == null) {
            System.out.println("Cyclist not found with ID: {}"+ generalResultRequestDto.cyclistId());
            throw new EntityNotFoundException("Cyclist not found with ID: " + generalResultRequestDto.cyclistId());
        }
        if (competition == null) {
            System.out.println("Competition not found with ID: {}"+ generalResultRequestDto.competitionId());

            throw new EntityNotFoundException("Competition not found with ID: " + generalResultRequestDto.competitionId());
        }

        GeneralResult generalResult = GeneralResultMapper.INSTANCE.toEntity(generalResultRequestDto);
        generalResult.setCompetition(competition);
        generalResult.setCyclist(cyclist);
        GeneralResultId id = new GeneralResultId(generalResultRequestDto.cyclistId(), generalResultRequestDto.competitionId());
        generalResult.setId(id);
        GeneralResult savedGeneralResult = generalResultRepository.save(generalResult);

        logger.info("Successfully saved General Result with ID: {}", savedGeneralResult.getId());
        return GeneralResultMapper.INSTANCE.toDto(savedGeneralResult);

    }



    @Transactional
    public List<GeneralResultResponseDto> findAll() {
        return generalResultRepository.findAll().stream()
                .map(GeneralResultMapper.INSTANCE::toDto)
                .toList();
    }

    @Transactional
    public GeneralResultResponseDto update(GeneralResultRequestDto generalResultRequestDto, GeneralResultId id) {
     return null;
    }

    @Transactional
    public Optional<GeneralResultResponseDto> findById(GeneralResultId id) {
        return generalResultRepository.findById(id)
                .map(GeneralResultMapper.INSTANCE::toDto);
    }
    @Transactional
    public void deleteById(GeneralResultId id) {
        generalResultRepository.deleteById(id);
    }
}
