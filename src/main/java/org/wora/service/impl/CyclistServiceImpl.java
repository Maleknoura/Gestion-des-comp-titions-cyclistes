package org.wora.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wora.Entity.Competition;
import org.wora.Entity.Cyclist;
import org.wora.repository.CyclistRepository;
import org.wora.service.Api.CyclistService;

import java.util.List;
import java.util.Optional;

@Service
public class CyclistServiceImpl implements CyclistService {
    @Autowired
    private CyclistRepository cyclistRepository;


    @Transactional
    @Override
    public Cyclist save( Cyclist cyclist) {
        return cyclistRepository.save(cyclist);
    }

    @Override
    public List<Cyclist> findall() {
        return cyclistRepository.findAll();
    }

    @Override
    public Optional<Cyclist> findbyId(long id) {
        return cyclistRepository.findById(id);
    }

    @Override
    public Cyclist update(Cyclist cyclist) {
        return cyclistRepository.save(cyclist);
    }

//    @Override
//    public List<Cyclist> findCyclistByCompetition(Competition competition) {
//        return cyclistRepository.findCyclistsByCompetition(competition);
//    }
}
