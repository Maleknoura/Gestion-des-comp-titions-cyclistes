package org.wora.stage;



import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wora.stage.dto.StageRequestDto;
import org.wora.stage.dto.StageResponseDto;
import org.wora.team.TeamServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/stages")
public class StageController {

    private final StageServiceImpl stageService;

    @Autowired
    public StageController( StageServiceImpl stageService) {
        this.stageService = stageService;
    }
    @PostMapping
    public ResponseEntity<StageResponseDto> createStage(@RequestBody @Valid StageRequestDto stageRequestDto) {
        StageResponseDto createdStage = stageService.save(stageRequestDto);
        return new ResponseEntity<>(createdStage, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StageResponseDto>> getAllStages() {
        List<StageResponseDto> stages = stageService.findAll();
        return new ResponseEntity<>(stages, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StageResponseDto> getStageById(@PathVariable Long id) {
        Optional<StageResponseDto> stage = stageService.findById(id);
        if (stage.isPresent()) {
            return new ResponseEntity<>(stage.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<StageResponseDto> updateStage(@PathVariable @Valid Long id, @RequestBody StageRequestDto stageRequestDto) {
        StageResponseDto updatedStage = stageService.update(stageRequestDto, id);
        if (updatedStage != null) {
            return new ResponseEntity<>(updatedStage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStage(@PathVariable Long id) {
        stageService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

