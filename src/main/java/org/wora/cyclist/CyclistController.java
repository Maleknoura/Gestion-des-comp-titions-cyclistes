package org.wora.cyclist;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wora.cyclist.dto.CyclistRequestDto;
import org.wora.cyclist.dto.CyclistResponseDto;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cyclists")
public class CyclistController {

    @Autowired
    private CyclistServiceImpl cyclistService;

    @PostMapping
    public ResponseEntity<CyclistResponseDto> createCyclist(@RequestBody CyclistRequestDto cyclistRequestDto) {
        CyclistResponseDto createdCyclist = cyclistService.save(cyclistRequestDto);
        return new ResponseEntity<>(createdCyclist, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CyclistResponseDto>> getAllCyclists() {
        List<CyclistResponseDto> cyclists = cyclistService.findAll();
        return new ResponseEntity<>(cyclists, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CyclistResponseDto> getCyclistById(@PathVariable Long id) {
        return cyclistService.findById(id)
                .map(cyclist -> new ResponseEntity<>(cyclist, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CyclistResponseDto> updateCyclist(
            @PathVariable Long id,
            @RequestBody CyclistRequestDto cyclistRequestDto) {
        CyclistResponseDto updatedCyclist = cyclistService.update(cyclistRequestDto, id);
        return updatedCyclist != null
                ? new ResponseEntity<>(updatedCyclist, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCyclist(@PathVariable Long id) {
        cyclistService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

