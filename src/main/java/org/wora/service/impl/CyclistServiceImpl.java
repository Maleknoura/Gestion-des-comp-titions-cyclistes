package org.wora.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wora.Entity.Cyclist;
import org.wora.repository.CyclistRepository;
import org.wora.service.CyclistService;

@Service
public class CyclistServiceImpl implements CyclistService {

    private final CyclistRepository cyclistRepository;

    @Autowired
    public CyclistServiceImpl(CyclistRepository cyclistRepository) {
        this.cyclistRepository = cyclistRepository;
    }

    @Override
    public Cyclist save(Cyclist cyclist) {
        return null;
    }
}
