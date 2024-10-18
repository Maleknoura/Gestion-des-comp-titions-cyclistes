package org.wora.service.impl;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wora.Entity.Competition;
import org.wora.repository.CompetitionRepository;
import org.wora.service.Api.CompetitionService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CompetitionServiceImpl implements CompetitionService {
    @Autowired
    private CompetitionRepository competitionRepository;


    public Competition save(@Valid Competition competition){
        return competitionRepository.save(competition);
    }

    @Override
    public List<Competition> findAll() {
        return competitionRepository.findAll();
    }

    @Override
    public Optional<Competition> findById(Long id) {
        return competitionRepository.findById(id);
    }

    @Override
    public Competition update(Competition competition) {
        return competitionRepository.save(competition);
    }
    @Override
    public List<Competition> findByDateRange(LocalDate startDate, LocalDate endDate) {
        return competitionRepository.findByStartDateBetween(startDate, endDate);
    }

    @Override
    public List<Competition> findByLocation(String location) {
        return competitionRepository.findByLocation(location);
    }
}
