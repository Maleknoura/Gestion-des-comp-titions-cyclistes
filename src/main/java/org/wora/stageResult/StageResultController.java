package org.wora.stageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wora.stageResult.dto.StageResultRequestDto;
import org.wora.stageResult.dto.StageResultResponseDto;
import org.wora.stageResult.embeddabls.StageResultId;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stage-results")
public class StageResultController {

    @Autowired
    private StageResultServiceImpl stageResultService;


    @GetMapping
    public ResponseEntity<List<StageResultResponseDto>> getAllStageResults() {
        List<StageResultResponseDto> stageResults = stageResultService.findAll();
        return ResponseEntity.ok(stageResults);
    }

    @GetMapping("/{stageId}/{cyclistId}")
    public ResponseEntity<StageResultResponseDto> getStageResultById(@PathVariable Long stageId, @PathVariable Long cyclistId) {
        StageResultId id = new StageResultId(cyclistId, stageId);
        return stageResultService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StageResultResponseDto> createStageResult(@RequestBody StageResultRequestDto stageResultRequestDto) {
        StageResultResponseDto createdStageResult = stageResultService.save(stageResultRequestDto);
        return ResponseEntity.ok(createdStageResult);
    }

    @DeleteMapping("/{stageId}/{cyclistId}")
    public ResponseEntity<Void> deleteStageResult(@PathVariable Long stageId, @PathVariable Long cyclistId) {
        StageResultId id = new StageResultId(cyclistId, stageId);
        stageResultService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
