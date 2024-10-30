package org.wora.generalResult;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wora.generalResult.dto.GeneralResultRequestDto;
import org.wora.generalResult.dto.GeneralResultResponseDto;
import org.wora.generalResult.embeddabls.GeneralResultId;


import java.util.List;

@RestController
@RequestMapping("/api/v1/general-results")
public class GeneralResultController {

    @Autowired
    private GeneralResultServiceImpl generalResultService;

    @GetMapping
    public ResponseEntity<List<GeneralResultResponseDto>> getAllGeneralResults() {
        List<GeneralResultResponseDto> results = generalResultService.findAll();
        return ResponseEntity.ok(results);
    }

    @GetMapping("/{competitionId}/{cyclistId}")
    public ResponseEntity<GeneralResultResponseDto> getGeneralResultByIds(@PathVariable Long competitionId, @PathVariable Long cyclistId) {
        GeneralResultId id = new GeneralResultId(cyclistId, competitionId);
        return generalResultService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<GeneralResultResponseDto> createGeneralResult(@RequestBody GeneralResultRequestDto generalResultRequestDto) {
        GeneralResultResponseDto createdResult = generalResultService.save(generalResultRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdResult);
    }


    @DeleteMapping("/{competitionId}/{cyclistId}")
    public ResponseEntity<Void> deleteGeneralResult(@PathVariable Long competitionId, @PathVariable Long cyclistId) {
        GeneralResultId id = new GeneralResultId(cyclistId, competitionId);
        generalResultService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

