package org.wora.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.wora.competition.Competition;
import org.wora.competition.CompetitionRepository;
import org.wora.competition.CompetitionServiceImpl;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)

class CompetitionServiceImplTest {
    @InjectMocks
    private CompetitionServiceImpl competitionService;
    @Mock
    private CompetitionRepository competitionRepository;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }
@Test
@DisplayName("Should return saved competition when given valid data")

    void save_ShouldReturnCompetition_WhenGivenValidData(){
    Competition competition = new Competition("RedCity", LocalDate.of(2024, 11, 1), LocalDate.of(2024, 12, 2), "Marrakech");

    when(competitionService.save(any(Competition.class))).thenReturn(competition);

    Competition competitionSavedList=competitionService.save(competition);

    assertNotNull(competitionSavedList);
    assertEquals("RedCity",competitionSavedList.getName());
    assertEquals(LocalDate.of(2024, 11, 1), competitionSavedList.getStartDate());
    assertEquals(LocalDate.of(2024, 12, 2), competitionSavedList.getEndDate());
    assertEquals("Marrakech",competitionSavedList.getLocation());

}
}