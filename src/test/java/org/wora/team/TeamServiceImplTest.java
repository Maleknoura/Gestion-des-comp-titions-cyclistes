package org.wora.team;

import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.wora.team.dto.TeamRequestDto;
import org.wora.team.dto.TeamResponseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TeamServiceImplTest {

    @InjectMocks
    private TeamServiceImpl teamService;

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private TeamMapper teamMapper;

    private TeamRequestDto teamRequestDto;
    private TeamResponseDto teamResponseDto;
    private Team team;





    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        teamRequestDto = new TeamRequestDto("Team A");
        team = new Team();
        team.setId(1L);
        team.setName("Team A");
        teamResponseDto = new TeamResponseDto("Team A");
    }





    @Test
    void save_shouldReturnSavedTeamResponseDto() {
        when(teamMapper.toEntity(teamRequestDto)).thenReturn(team);
        when(teamRepository.save(team)).thenReturn(team);
        when(teamMapper.toDto(team)).thenReturn(teamResponseDto);

        TeamResponseDto result = teamService.save(teamRequestDto);

        assertNotNull(result);
        assertEquals("Team A", result.name());
        verify(teamRepository).save(any(Team.class));
    }


    @Test
    void findAll_shouldReturnListOfTeamResponseDto() {
        List<Team> teams = new ArrayList<>();
        teams.add(team);

        when(teamRepository.findAll()).thenReturn(teams);
        when(teamMapper.toDto(any(Team.class))).thenReturn(teamResponseDto);

        List<TeamResponseDto> result = teamService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Team A", result.get(0).name());
    }

    @Test
    void findById_shouldReturnOptionalOfTeamResponseDto() {
        when(teamRepository.findById(1L)).thenReturn(Optional.of(team));
        when(teamMapper.toDto(team)).thenReturn(teamResponseDto);

        Optional<TeamResponseDto> result = teamService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals("Team A", result.get().name());
    }

    @Test
    void update_shouldReturnUpdatedTeamResponseDto() {
        Long teamId = 1L;
        TeamRequestDto updateRequest = new TeamRequestDto("Updated Team");

        when(teamRepository.existsById(teamId)).thenReturn(true);

        when(teamRepository.findById(teamId)).thenReturn(Optional.of(team));

        Team updatedTeam = new Team();
        updatedTeam.setId(teamId);
        updatedTeam.setName("Updated Team");
        when(teamRepository.save(any(Team.class))).thenReturn(updatedTeam);

        when(teamMapper.toDto(any(Team.class))).thenReturn(new TeamResponseDto("Updated Team"));

        TeamResponseDto result = teamService.update(updateRequest, teamId);

        assertNotNull(result);
        assertEquals("Updated Team", result.name());

        verify(teamRepository).existsById(teamId);
        verify(teamRepository).findById(teamId);
        verify(teamMapper).updateEntityFromDto(eq(updateRequest), any(Team.class));
        verify(teamRepository).save(any(Team.class));
        verify(teamMapper).toDto(any(Team.class));
    }

    @Test
    void update_shouldReturnNullWhenTeamDoesNotExist() {
        Long teamId = 1L;
        TeamRequestDto updateRequest = new TeamRequestDto("Updated Team");
        when(teamRepository.existsById(teamId)).thenReturn(false);

        TeamResponseDto result = teamService.update(updateRequest, teamId);

        assertNull(result);
        verify(teamRepository).existsById(teamId);
        verify(teamRepository, never()).findById(any());
        verify(teamMapper, never()).updateEntityFromDto(any(), any());
        verify(teamRepository, never()).save(any());
    }

    @Test
    void deleteById_shouldCallDeleteOnRepository() {
        doNothing().when(teamRepository).deleteById(1L);

        teamService.deleteById(1L);

        verify(teamRepository).deleteById(1L);
    }
}
