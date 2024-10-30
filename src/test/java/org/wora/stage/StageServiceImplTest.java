package org.wora.stage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.wora.competition.Competition;
import org.wora.competition.CompetitionRepository;
import org.wora.stage.dto.StageRequestDto;
import org.wora.stage.dto.StageResponseDto;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class StageServiceImplTest {

    @Mock
    private StageRepository stageRepository;

    @Mock
    private CompetitionRepository competitionRepository;

    @InjectMocks
    private StageServiceImpl stageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_WhenCompetitionExists_ShouldReturnStageResponseDto() {
        StageRequestDto requestDto = new StageRequestDto(
                "Stage 1",
                100.0,
                LocalDate.now().plusDays(1),
                "Tour de France"
        );

        Competition competition = new Competition();
        competition.setName("Tour de France");

        Stage savedStage = new Stage();
        savedStage.setName("Stage 1");
        savedStage.setDistance(100.0);
        savedStage.setDate(LocalDate.now().plusDays(1));
        savedStage.setCompetition(competition);

        when(competitionRepository.findByName("Tour de France")).thenReturn(Optional.of(competition));
        when(stageRepository.save(any(Stage.class))).thenReturn(savedStage);

        StageResponseDto result = stageService.save(requestDto);

        assertNotNull(result);
        assertEquals("Stage 1", result.name());
        assertEquals(100.0, result.distance());
        verify(competitionRepository).findByName("Tour de France");
        verify(stageRepository).save(any(Stage.class));
    }

    @Test
    void save_WhenCompetitionNotFound_ShouldReturnErrorResponse() {
        StageRequestDto requestDto = new StageRequestDto(
                "Stage 1",
                100.0,
                LocalDate.now().plusDays(1),
                "Invalid Competition"
        );

        when(competitionRepository.findByName("Invalid Competition")).thenReturn(Optional.empty());

        StageResponseDto result = stageService.save(requestDto);

        assertEquals("Competition not found", result.name());
        assertEquals(0, result.distance());
        assertNull(result.date());
        verify(stageRepository, never()).save(any(Stage.class));
    }

    @Test
    void findAll_ShouldReturnListOfStageResponseDto() {

        Stage stage1 = new Stage();
        stage1.setName("Stage 1");
        Stage stage2 = new Stage();
        stage2.setName("Stage 2");

        when(stageRepository.findAll()).thenReturn(Arrays.asList(stage1, stage2));

        List<StageResponseDto> result = stageService.findAll();


        assertNotNull(result);
        assertEquals(2, result.size());
        verify(stageRepository).findAll();
    }

    @Test
    void update_WhenStageExists_ShouldReturnUpdatedStageResponseDto() {

        Long stageId = 1L;
        StageRequestDto requestDto = new StageRequestDto(
                "Updated Stage",
                150.0,
                LocalDate.now().plusDays(1),
                "Tour de France"
        );

        Stage existingStage = new Stage();
        existingStage.setId(stageId);
        existingStage.setName("Old Stage");

        when(stageRepository.findById(stageId)).thenReturn(Optional.of(existingStage));
        when(stageRepository.save(any(Stage.class))).thenReturn(existingStage);

        StageResponseDto result = stageService.update(requestDto, stageId);

        assertNotNull(result);
        verify(stageRepository).findById(stageId);
        verify(stageRepository).save(any(Stage.class));
    }

    @Test
    void update_WhenStageNotFound_ShouldReturnErrorResponse() {
        Long stageId = 1L;
        StageRequestDto requestDto = new StageRequestDto(
                "Updated Stage",
                150.0,
                LocalDate.now().plusDays(1),
                "Tour de France"
        );

        when(stageRepository.findById(stageId)).thenReturn(Optional.empty());


        StageResponseDto result = stageService.update(requestDto, stageId);


        assertEquals("Stage not found", result.name());
        assertEquals(0, result.distance());
        assertNull(result.date());
        verify(stageRepository, never()).save(any(Stage.class));
    }

    @Test
    void findById_WhenStageExists_ShouldReturnStageResponseDto() {

        Long stageId = 1L;
        Stage stage = new Stage();
        stage.setId(stageId);
        stage.setName("Stage 1");

        when(stageRepository.findById(stageId)).thenReturn(Optional.of(stage));


        Optional<StageResponseDto> result = stageService.findById(stageId);


        assertTrue(result.isPresent());
        assertEquals("Stage 1", result.get().name());
        verify(stageRepository).findById(stageId);
    }

    @Test
    void findById_WhenStageNotFound_ShouldReturnEmpty() {

        Long stageId = 1L;
        when(stageRepository.findById(stageId)).thenReturn(Optional.empty());


        Optional<StageResponseDto> result = stageService.findById(stageId);


        assertTrue(result.isEmpty());
        verify(stageRepository).findById(stageId);
    }

    @Test
    void deleteById_ShouldCallRepository() {

        Long stageId = 1L;

        stageService.deleteById(stageId);

        verify(stageRepository).deleteById(stageId);
    }
}


