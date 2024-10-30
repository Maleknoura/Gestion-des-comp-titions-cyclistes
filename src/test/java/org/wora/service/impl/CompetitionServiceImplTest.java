package org.wora.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.wora.competition.Competition;
import org.wora.competition.CompetitionMapper;
import org.wora.competition.CompetitionRepository;
import org.wora.competition.CompetitionServiceImpl;
import org.wora.competition.dto.CompetitionRequestDto;
import org.wora.competition.dto.CompetitionResponseDto;
import org.wora.generalResult.GeneralResultRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompetitionServiceImplTest {

    @InjectMocks
    private CompetitionServiceImpl competitionService;

    @Mock
    private CompetitionRepository competitionRepository;

    @Mock
    private GeneralResultRepository generalResultRepository;

    @Mock
    private CompetitionMapper competitionMapper;

    private CompetitionRequestDto createCompetitionRequestDto() {
        return new CompetitionRequestDto(
                "Competition Name",
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(5),
                "Location"
        );
    }

    private Competition createCompetition() {
        return new Competition(
                "Competition Name",
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(5),
                "Location"
        );
    }

    @Test
    @DisplayName("Should save and return competition when given valid data")
    void save_ShouldReturnSavedCompetition_WhenGivenValidData() {
        CompetitionRequestDto competitionRequestDto = createCompetitionRequestDto();
        Competition competition = createCompetition();
        Competition savedCompetition = createCompetition();

        when(competitionMapper.toEntity(competitionRequestDto)).thenReturn(competition);
        when(competitionRepository.save(competition)).thenReturn(savedCompetition);
        when(competitionMapper.toDto(savedCompetition)).thenReturn(new CompetitionResponseDto(
                savedCompetition.getName(),
                savedCompetition.getStartDate(),
                savedCompetition.getEndDate(),
                savedCompetition.getLocation(),
                new HashSet<>()
        ));

        CompetitionResponseDto responseDto = competitionService.save(competitionRequestDto);

        assertNotNull(responseDto);
        assertEquals(competitionRequestDto.name(), responseDto.name());
        verify(competitionMapper).toEntity(competitionRequestDto);
        verify(competitionRepository).save(competition);
        verify(competitionMapper).toDto(savedCompetition);
    }

    @Test
    @DisplayName("Should return all competitions")
    void findAll_ShouldReturnListOfCompetitions() {
        Competition competition1 = createCompetition();
        Competition competition2 = createCompetition();
        when(competitionRepository.findAll()).thenReturn(List.of(competition1, competition2));
        when(competitionMapper.toDto(competition1)).thenReturn(new CompetitionResponseDto(
                competition1.getName(),
                competition1.getStartDate(),
                competition1.getEndDate(),
                competition1.getLocation(),
                new HashSet<>()
        ));
        when(competitionMapper.toDto(competition2)).thenReturn(new CompetitionResponseDto(
                competition2.getName(),
                competition2.getStartDate(),
                competition2.getEndDate(),
                competition2.getLocation(),
                new HashSet<>()
        ));

        List<CompetitionResponseDto> competitions = competitionService.findAll();

        assertNotNull(competitions);
        assertEquals(2, competitions.size());
        verify(competitionRepository).findAll();
    }

    @Test
    @DisplayName("Should return competition when found by ID")
    void findById_ShouldReturnCompetition_WhenCompetitionExists() {
        Competition competition = createCompetition();
        when(competitionRepository.findById(1L)).thenReturn(Optional.of(competition));
        when(competitionMapper.toDto(competition)).thenReturn(new CompetitionResponseDto(
                competition.getName(),
                competition.getStartDate(),
                competition.getEndDate(),
                competition.getLocation(),
                new HashSet<>()
        ));

        Optional<CompetitionResponseDto> foundCompetition = competitionService.findById(1L);

        assertTrue(foundCompetition.isPresent());
        assertEquals(competition.getName(), foundCompetition.get().name());
        verify(competitionRepository).findById(1L);
    }


    @Test
    @DisplayName("Should return competitions within date range")
    void findByDateRange_ShouldReturnCompetitions_WhenWithinDateRange() {
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);
        Competition competition = createCompetition();
        when(competitionRepository.findByStartDateBetween(startDate, endDate)).thenReturn(List.of(competition));
        when(competitionMapper.toDto(competition)).thenReturn(new CompetitionResponseDto(
                competition.getName(),
                competition.getStartDate(),
                competition.getEndDate(),
                competition.getLocation(),
                new HashSet<>()
        ));

        List<CompetitionResponseDto> competitions = competitionService.findByDateRange(startDate, endDate);

        assertNotNull(competitions);
        assertEquals(1, competitions.size());
        verify(competitionRepository).findByStartDateBetween(startDate, endDate);
    }



}
