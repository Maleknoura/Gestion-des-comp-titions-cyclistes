package org.wora.competition;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wora.competition.dto.CompetitionRequestDto;

@RestController
@RequestMapping("/competitions")
public class CompetitionController {

    @Autowired
    private CompetitionServiceImpl competitionService;

    @PostMapping
    public ResponseEntity<Competition> createCompetition(@RequestBody CompetitionRequestDto createCompetitionDto) {
        Competition competition = new Competition(
                createCompetitionDto.getName(),
                createCompetitionDto.getStartDate(),
                createCompetitionDto.getEndDate(),
                createCompetitionDto.getLocation()
        );

        Competition savedCompetition = competitionService.save(competition);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCompetition);
    }
}

