package org.wora.cyclist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wora.common.GenericService;

import java.util.List;
import java.util.Optional;

@Service
public class CyclistServiceImpl implements GenericService<Cyclist, Long> {

    @Autowired
    private CyclistRepository cyclistRepository;

    @Override
    public Cyclist save(Cyclist cyclist) {
        return cyclistRepository.save(cyclist);
    }

    @Override
    public List<Cyclist> findAll() {
        return cyclistRepository.findAll();
    }

    @Override
    public Cyclist update(Cyclist cyclist) {
        return cyclistRepository.save(cyclist);
    }

    @Override
    public Optional<Cyclist> findById(Long id) {
        return cyclistRepository.findById(id);
    }
}
