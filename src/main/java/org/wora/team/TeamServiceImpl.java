package org.wora.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.wora.common.GenericService;
import org.wora.team.dto.TeamRequestDto;
import org.wora.team.dto.TeamResponseDto;

import java.util.List;
import java.util.Optional;

@Validated
@Service
public class TeamServiceImpl implements GenericService<TeamRequestDto, TeamResponseDto, Long> {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamMapper teamMapper;

    @Override
    public TeamResponseDto save(TeamRequestDto dto) {
        Team team = teamMapper.toEntity(dto);
        Team savedTeam = teamRepository.save(team);
        return teamMapper.toDto(savedTeam);
    }

    @Override
    public List<TeamResponseDto> findAll() {
        List<Team> teams = teamRepository.findAll();
        return teams.stream()
                .map(teamMapper::toDto)
                .toList();
    }

    @Override
    public Optional<TeamResponseDto> findById(Long id) {
        return teamRepository.findById(id)
                .map(teamMapper::toDto);
    }

    @Override
    public TeamResponseDto update(TeamRequestDto dto, Long id) {
        if (!teamRepository.existsById(id)) {
            return null;
        }
        Team existingTeam = teamRepository.findById(id).orElseThrow();
        teamMapper.updateEntityFromDto(dto, existingTeam);
        Team updatedTeam = teamRepository.save(existingTeam);
        return teamMapper.toDto(updatedTeam);
    }

    @Override
    public void deleteById(Long id) {
        teamRepository.deleteById(id);
    }
}
