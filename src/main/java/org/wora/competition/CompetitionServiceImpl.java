package org.wora.competition;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.wora.competition.dto.CompetitionRequestDto;
import org.wora.competition.dto.CompetitionResponseDto;
import org.wora.generalResult.GeneralResult;
import org.wora.generalResult.GeneralResultRepository;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wora.common.GenericService;
import org.wora.generalResult.dto.GeneralResultResponseDto;

@Service
@Validated
public class CompetitionServiceImpl implements GenericService<CompetitionRequestDto, CompetitionResponseDto, Long> {

    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private GeneralResultRepository generalResultRepository;

    @Autowired
    private  CompetitionMapper competitionMapper;

    private static final Logger logger = LoggerFactory.getLogger(CompetitionServiceImpl.class);

    public CompetitionResponseDto save(CompetitionRequestDto competitionRequestDto) {
        Competition competition = competitionMapper.toEntity(competitionRequestDto);
        Competition savedCompetition = competitionRepository.save(competition);
        return competitionMapper.toDto(savedCompetition);
    }


    @Override
    public List<CompetitionResponseDto> findAll() {

        try{
            List<Competition>competitions= competitionRepository.findAll();
                 return competitions.stream()
                    .map(competitionMapper::toDto)
                    .collect(Collectors.toList());
        }catch (Exception e ){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    @Override
    public Optional<CompetitionResponseDto> findById(Long id) {
        return competitionRepository.findById(id)
                .map(competitionMapper::toDto);
    }



    @Override
    public CompetitionResponseDto update(CompetitionRequestDto competitionRequestDto, Long id) {
        logger.info("Updating competition with id: " + id);

        Optional<Competition> optionalCompetition = competitionRepository.findById(id);
        if (optionalCompetition.isEmpty()) {
            return null;
        }

        Competition existingCompetition = optionalCompetition.get();

        CompetitionMapper.INSTANCE.updateEntityFromDto(competitionRequestDto, existingCompetition);

        Competition updatedCompetition = competitionRepository.save(existingCompetition);

        return CompetitionMapper.INSTANCE.toDto(updatedCompetition);
    }


    @Override
    public void deleteById(Long id) {
        competitionRepository.deleteById(id);
    }
    public List<CompetitionResponseDto> findByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Competition> competitions = competitionRepository.findByStartDateBetween(startDate, endDate);
        return competitions.stream()
                .map(competitionMapper::toDto)
                .toList();
    }

    public List<CompetitionResponseDto> findByLocation(String location) {
        List<Competition> competitions = competitionRepository.findByLocation(location);
        return competitions.stream()
                .map(competitionMapper::toDto)
                .toList();
    }

//    public List<GeneralResultResponseDto> findCyclistRankings(Long competitionId) {
//        List<GeneralResult> generalResults = generalResultRepository.findByCompetitionIdOrderByGeneralRankAsc(competitionId);
//        return generalResults.stream()
//                .map(generalResultMapper::toDto)
//                .toList();
//    }
}
