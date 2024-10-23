package org.wora.competition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wora.competition.dto.CompetitionRequestDto;
import org.wora.competition.dto.CompetitionResponseDto;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/competitions")
public class CompetitionController {

    private static final Logger log = LoggerFactory.getLogger(CompetitionController.class);
    private CompetitionServiceImpl competitionService;

    public CompetitionController(CompetitionServiceImpl service) {
//        System.out.println("this component initialezd");
//        log.info("initialize");
        this.competitionService = service;
    }

    @PostMapping
    public ResponseEntity<CompetitionResponseDto> createCompetition(@RequestBody CompetitionRequestDto competitionRequestDto) {
        CompetitionResponseDto createdCompetition = competitionService.save(competitionRequestDto);
        return new ResponseEntity<>(createdCompetition, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CompetitionResponseDto>> getAllCompetitions() {
        List<CompetitionResponseDto> competitions = competitionService.findAll();
        return new ResponseEntity<>(competitions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompetitionResponseDto> getCompetitionById(@PathVariable Long id) {
        return competitionService.findById(id)
                .map(competition -> new ResponseEntity<>(competition, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompetitionResponseDto> updateCompetition(
            @PathVariable Long id,
            @RequestBody CompetitionRequestDto competitionRequestDto) {

        CompetitionResponseDto updatedCompetition = competitionService.update(competitionRequestDto, id);

        if (updatedCompetition != null) {
            return new ResponseEntity<>(updatedCompetition, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompetition(@PathVariable Long id) {
        competitionService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<CompetitionResponseDto>> getCompetitionsByDateRange(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        List<CompetitionResponseDto> competitions = competitionService.findByDateRange(startDate, endDate);
        return new ResponseEntity<>(competitions, HttpStatus.OK);
    }

    @GetMapping("/location")
    public ResponseEntity<List<CompetitionResponseDto>> getCompetitionsByLocation(@RequestParam String location) {
        List<CompetitionResponseDto> competitions = competitionService.findByLocation(location);
        return new ResponseEntity<>(competitions, HttpStatus.OK);
    }

    // @GetMapping("/{competitionId}/rankings")
    // public ResponseEntity<List<GeneralResultResponseDto>> getCyclistRankings(@PathVariable Long competitionId) {
    //     List<GeneralResultResponseDto> rankings = competitionService.findCyclistRankings(competitionId);
    //     return new ResponseEntity<>(rankings, HttpStatus.OK);
    // }
}
