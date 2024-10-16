package org.wora.service.impl;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.wora.Entity.Cyclist;
import org.wora.repository.CyclistRepository;
import org.wora.service.CyclistService;

@Service
public class CyclistServiceImpl implements CyclistService {
    @Autowired
    private CyclistRepository cyclistRepository;


    @Transactional
    @Override
    public Cyclist save( Cyclist cyclist) {
        return cyclistRepository.save(cyclist);
    }
}
