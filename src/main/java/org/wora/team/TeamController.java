package org.wora.team;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.wora.team.dto.TeamRequestDto;
import org.wora.team.dto.TeamResponseDto;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teams")
public class TeamController {

    private static final Logger log = LoggerFactory.getLogger(TeamController.class);
    private final TeamServiceImpl teamService;

    @Autowired
    public TeamController(TeamServiceImpl service) {
        this.teamService = service;
    }

    @PostMapping
    public ResponseEntity<TeamResponseDto> createTeam( @RequestBody @Valid TeamRequestDto teamRequestDto) {
        TeamResponseDto createdTeam = teamService.save(teamRequestDto);
        return new ResponseEntity<>(createdTeam, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TeamResponseDto>> getAllTeams() {
        List<TeamResponseDto> teams = teamService.findAll();
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamResponseDto> getTeamById(@PathVariable Long id) {
        return teamService.findById(id)
                .map(team -> new ResponseEntity<>(team, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamResponseDto> updateTeam(
            @PathVariable Long id,
            @Valid
            @RequestBody TeamRequestDto teamRequestDto) {

        TeamResponseDto updatedTeam = teamService.update(teamRequestDto, id);

        if (updatedTeam != null) {
            return new ResponseEntity<>(updatedTeam, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        teamService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
