package org.wora.competition;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wora.competition.dto.CompetitionRequestDto;
import org.wora.competition.dto.CompetitionResponseDto;
import org.wora.generalResult.GeneralResult;
import org.wora.generalResult.GeneralResultRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wora.common.GenericService;

@Service
public class CompetitionServiceImpl implements GenericService<CompetitionRequestDto, CompetitionResponseDto, Long> {

    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private GeneralResultRepository generalResultRepository;

    @Autowired
    private ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(CompetitionServiceImpl.class);

    @Override
    public CompetitionResponseDto save(CompetitionRequestDto competitionRequestDto) {
        Competition competition = modelMapper.map(competitionRequestDto, Competition.class);
        Competition savedCompetition = competitionRepository.save(competition);
        return modelMapper.map(savedCompetition, CompetitionResponseDto.class);
    }

    @Override
    public List<CompetitionResponseDto> findAll() {
        return competitionRepository.findAll()
                .stream()
                .map(competition -> modelMapper.map(competition, CompetitionResponseDto.class))
                .toList();
    }

    @Override
    public Optional<CompetitionResponseDto> findById(Long id) {
        logger.info("logger initialized " + id);
        return competitionRepository.findById(id)
                .map(competition -> modelMapper.map(competition, CompetitionResponseDto.class));
    }

    @Override
    public CompetitionResponseDto update(CompetitionRequestDto competitionRequestDto, Long id) {
        logger.info("Updating competition with id: " + id);

        Optional<Competition> optionalCompetition = competitionRepository.findById(id);

        if (optionalCompetition.isEmpty()) {
            return null;
        }

        Competition existingCompetition = optionalCompetition.get();
        modelMapper.map(competitionRequestDto, existingCompetition);
        Competition updatedCompetition = competitionRepository.save(existingCompetition);

        return modelMapper.map(updatedCompetition, CompetitionResponseDto.class);
    }

    @Override
    public void deleteById(Long id) {
        competitionRepository.deleteById(id);
    }

    public List<CompetitionResponseDto> findByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Competition> competitions = competitionRepository.findByStartDateBetween(startDate, endDate);
        return competitions.stream()
                .map(competition -> modelMapper.map(competition, CompetitionResponseDto.class))
                .toList();
    }

    public List<CompetitionResponseDto> findByLocation(String location) {
        List<Competition> competitions = competitionRepository.findByLocation(location);
        return competitions.stream()
                .map(competition -> modelMapper.map(competition, CompetitionResponseDto.class))
                .toList();
    }

    // public List<GeneralResultResponseDto> findCyclistRankings(Long competitionId) {
    //     List<GeneralResult> generalResults = generalResultRepository.findByCompetitionIdOrderByGeneralRankAsc(competitionId);
    //     return generalResults.stream()
    //             .map(result -> modelMapper.map(result, GeneralResultResponseDto.class))
    //             .toList();
    // }
}
